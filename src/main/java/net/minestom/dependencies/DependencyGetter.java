package net.minestom.dependencies;

import net.minestom.dependencies.maven.MavenRepository;
import net.minestom.dependencies.maven.MavenResolver;
import java.io.File;
import java.nio.file.Path;
import java.util.List;
import java.util.ArrayList;

/**
 * The dependency getter class.
 */
public class DependencyGetter {
  
  /**
   * The list of resolvers.
   */
  private List<DependencyResolver> resolverList = new ArrayList<>();

  /**
   * Addes a resolver to this object.
   * 
   * @param resolver A new resolver to add
   */
  public void addResolver(DependencyResolver resolver) {
    resolverList.add(resolver);
  }

  /**
   * Addes a maven resolver from input maven repositories.
   * 
   * @param repositories The maven repositories to be resolved
   */
  public void addMavenResolver(List<MavenRepository> repositories) {
    this.addResolver( new MavenResolver(repositories));
  }

  /**
   * Resolves a dependency.
   * 
   * @param id The id
   * @param targetFolder The target folder
   * 
   * @return The resolved dependency
   */
  public ResolvedDependency get(String id, Path targetFolder) throws UnresolvedDependencyException {
    for(DependencyResolver resolver : this.resolverList) {
      try{
        return resolver.resolve(id, targetFolder);
      }catch(UnresolvedDependencyException e) {
        // just go to the next resolver
      }
    }

    // Not found anywhere
    throw new UnresolvedDependencyException("Could not find " + id + "inside resolver list");
  }
}