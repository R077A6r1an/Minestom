package net.minestom.dependencies;

/**
 * Thrown when a given dependency could not be found inside the given resolvers.
 */
public class UnresolvedDependencyException extends Exception {
  
  /**
   * Constructor for this class.
   * 
   * @param msg The message and cause
   * @param cause The cause of this exception
   */
  public UnresolvedDependencyException(String msg, Throwable cause) {
    super(msg, cause);
  }

  /**
   * Constructor without cause.
   * 
   * @param msg The message for the failure
   */
  public UnresolvedDependencyException(String msg) {
    super(msg);
  }
}