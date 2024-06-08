package net.minestom.dependencies;

import java.net.URL;
import java.nio.file.Path;

/**
 * Resolves and downloads a dependency via its id.
 * Ids formats are up to the resolver.
 */
public interface DependencyResolver {
  
  /**
   * Resolve and download a dependency to local storage.
   * Allowed to avoid redownload if there is a local version cached.
   * 
   * @param id The id
   * @param targetFolder The target folder
   * 
   * @throws UnresolvedDependencyException if the dependency could not be resolved via this resolver
   */
  ResolvedDependency resolve(String id, Path targetFolder) throws UnresolvedDependencyException;
}
