package net.minestom.dependencies;

import java.net.URL;
import java.util.List;

/**
 * Resolved Dependency.
 * Holds its coordinates (group, artifact, version), which are allowed to be empty if needed
 *
 * The contentsLocation URL represents the location of the dependency, on local storage.
 */
public class ResolvedDependency {
  
  /**
   * The group id.
   */
  public String group;

  /**
   * The artifact id.
   */
  public String name;

  /**
   * The version.
   */
  public String version;

  /**
   * Contents location.
   */
  public URL contentsLocation;

  /**
   * Sub dependencies.
   */
  public List<ResolvedDependency> subdependencies;

  /**
   * Constructor for this class.
   * 
   * @param group The groupId
   * @param name The artifactId
   * @param version The version
   * @param loc The location
   * @param deps Other dependencies
   */
  public ResolvedDependency(String group, String name, String version, URL loc, List<ResolvedDependency> deps) {
    this.group = group;
    this.name = name;
    this.version = version;
    this.contentsLocation = loc;
    this.subdependencies = deps;
  }

  /**
   * Prints the dependency tree of this dependency.
   * 
   * @param ident The offset whitespaces.
   */
  public void printTree(String ident) {
    System.out.println("" + ident + "- " + this.group + ":" + this.name + ":" + this.version + " (" + this.contentsLocation + ")");
    for(ResolvedDependency dep : this.subdependencies) {
      dep.printTree(ident + "  ");
    }
  }

  /**
   * Prints the dependencies of this dependency with this one as
   * root dependency.
   */
  public void printTree() {
    this.printTree("");
  }

  /**
   * Returns the contents location.
   * 
   * @return The contents location
   */
  public URL getContentsLocation() {
    return this.contentsLocation;
  }

  /**
   * Returns the sub dependencies.
   */
  public List<ResolvedDependency> getSubdependencies() {
    return this.subdependencies;
  }
}