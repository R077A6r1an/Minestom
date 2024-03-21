package net.minestom.dependencies.maven;

import net.minestom.dependencies.DependencyResolver;
import net.minestom.dependencies.ResolvedDependency;
import net.minestom.dependencies.UnresolvedDependencyException;
import org.jboss.shrinkwrap.resolver.api.CoordinateParseException;
import org.jboss.shrinkwrap.resolver.api.NoResolvedResultException;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.jboss.shrinkwrap.resolver.api.maven.MavenResolvedArtifact;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Comparator;
import java.io.IOException;

/**
 * Resolves maven dependencies.
 * *Does not use the local maven repository, but uses the folder passed as an argument in resolve(String, File)*
 *
 * Creates a temporary folder `.tmp` inside the target folder to store a Maven `settings.xml` file to specify
 * a local repository and remote repositories.
 */
public class MavenResolver implements DependencyResolver {
  
  /**
   * The repository list this is able to resolve.
   */
  private List<MavenRepository> repositories;

  /**
   * Constructor for the maven resolver.
   * 
   * @param repositories The repositories that can be resolved by this object
   */
  public MavenResolver(List<MavenRepository> repositories) {
    this.repositories = repositories;
  }

  /**
   * Returns the list of repositories this object handles.
   * 
   * @return The list of the repositories that will be handled
   */
  public List<MavenRepository> getRepositories() {
    return this.repositories;
  }

  /**
   * Resolves a maven repository.
   * 
   * @param id The id to resolve
   * @param targetFolder The target folder to download it into
   * 
   * @return The resolved dependency object
   */
  @Override
  public ResolvedDependency resolve(String id, Path targetFolder) throws UnresolvedDependencyException {
    Path tmpFolder = targetFolder.resolve(".tmp");
    try{
      Files.createDirectories(tmpFolder);
      Path settingsFilePath = tmpFolder.resolve("settings.xml");
      
      // Create the list
      String repoList = "";
      for(MavenRepository repo : this.repositories) {
        repoList += "        <repository>\n";
        repoList += "          <id>" + repo.name + "</id>\n";
        repoList += "          <name>" + repo.name + "</name>\n";
        repoList += "          <url>" + repo.url.toExternalForm() + "</url>\n";
        repoList += "          <layout>default</layout>\n";
        repoList += "        </repository>\n";
      }

      // Now creates the settings file
      String settingsFile = "";
      settingsFile += "<settings xmlns=\"http://maven.apache.org/SETTINGS/1.1.0\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://maven.apache.org/SETTINGS/1.1.0 http://maven.apache.org/xsd/settings-1.1.0.xsd\">\n";
      settingsFile += "  <localRepository>" + targetFolder.toAbsolutePath() + "</localRepository>\n";
      settingsFile += "  <profiles>\n";
      settingsFile += "    <profile>\n";
      settingsFile += "      <id>dependency-getter-auto</id>\n";
      settingsFile += "      <repositories>\n";
      settingsFile += repoList;
      settingsFile += "      </repositories>\n";
      settingsFile += "    </profile>\n";
      settingsFile += "  </profiles>\n";
      settingsFile += "  <activeProfiles>\n";
      settingsFile += "    <activeProfile>dependency-getter-auto</activeProfile>\n";
      settingsFile += "  </activeProfiles>\n";
      settingsFile += "</settings>";

      Files.writeString(settingsFilePath, settingsFile);
      boolean hasMavenCentral = false;
      for(MavenRepository repo : this.repositories) {
        if(repo.url.sameFile(MavenRepository.Central.url)) {
          hasMavenCentral = true;
          break;
        }
      }

      var resolver = Maven.configureResolver().withMavenCentralRepo(hasMavenCentral).fromFile(settingsFilePath.toFile());
      var artifacts = resolver.resolve(id).withTransitivity().asResolvedArtifact();
      var dependencies = Arrays.stream(artifacts).skip(1).map(this::convertToDependency);
      var coords = artifacts[0].getCoordinate();
      return new ResolvedDependency(coords.getGroupId(), coords.getArtifactId(), coords.getVersion(), artifacts[0].asFile().toURI().toURL(), dependencies.toList());
    }catch(CoordinateParseException e) {
      throw new UnresolvedDependencyException("Failed to resolve " + id + " (not a Maven coordinate)", e);
    }catch(NoResolvedResultException e) {
      throw new UnresolvedDependencyException("Failed to resolve " + id, e);
    }catch(Exception e) {
      throw new UnresolvedDependencyException("Failed to resolve " + id, e);
    }finally{
      try{
        Files.walk(tmpFolder).sorted(Comparator.reverseOrder()).forEach(this::delete);
      }catch(Exception e) {}
    }
  }

  /**
   * Deletes all paths overgiven to the handler of above.
   * 
   * @param path The path to delete
   */
  private void delete(Path path) {
    try{
      Files.delete(path);
    }catch(Exception e) {}
  }

  /**
   * Converts the input artifact to a dependency object.
   * 
   * @param artifact The target artifact
   * 
   * @return The resolved dependency object
   */
  private ResolvedDependency convertToDependency(MavenResolvedArtifact artifact) {
    var coordinate = artifact.getCoordinate();
    try{
      return new ResolvedDependency(coordinate.getGroupId(), coordinate.getArtifactId(), coordinate.getVersion(), artifact.asFile().toURI().toURL(), new ArrayList<ResolvedDependency>());
    }catch(Exception e) {
      return null;
    }
  }

  /**
   * Returns the string representing this object.
   * 
   * @return The string representing this object
   */
  @Override
  public String toString() {
    return "MavenResolver";
  }
}