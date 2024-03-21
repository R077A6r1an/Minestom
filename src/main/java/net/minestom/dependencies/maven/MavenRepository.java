package net.minestom.dependencies.maven;

import java.net.URL;

/**
 * Represents a maven repository by its name and URL.
 * Equality/hashCode/toString is based only on the URL.
 * The name is only used as an id for the maven resolver, and shows up in resolution errors.
 */
public class MavenRepository {
  
  /**
   * The name of the repository.
   */
  String name;

  /**
   * The url of the repository.
   */
  URL url;

  /**
   * The maven central repository.
   */
  public static final MavenRepository Central = new MavenRepository("Central", "https://repo1.maven.org/maven2/");

  /**
   * The maven sonatype repository.
   */
  public static final MavenRepository Sonatype = new MavenRepository("Sonatype", "https://oss.sonatype.org/content/repositories/releases/");
  
  /**
   * The JCenter repository.
   */
  public static final MavenRepository JCentrer = new MavenRepository("JCenter", "https://jcenter.bintray.com/");

  /**
   * The Jitpack repository.
   */
  public static final MavenRepository Jitpack = new MavenRepository("Jitpack.io", "https://jitpack.io/");

  /**
   * Constructor for the maven repository.
   * 
   * @param name The repository name
   */
  public MavenRepository(String name, String url) {
    this.name = name;
    
    // Now check if URL is ok, and if not, change it
    String inUrl = url.endsWith("/") ? url : url + "/";
    try{
      this.url = new URL(inUrl);
    }catch(Exception e) {
      throw new NullPointerException("URL is not parseable");
    }
  }

  /**
   * Checks if the input repository is the same as this one.
   * 
   * @param other The other repository to check
   * 
   * @return If the object is equals, or not
   */
  @Override
  public boolean equals(Object other) {
    if(((Object)this) == other) {
      return true;
    }

    if(this.getClass() != other.getClass()) {
      return false;
    }

    MavenRepository repo = ((MavenRepository)other);
    if(!this.url.equals(repo.url)) {
      return false;
    }

    // Match
    return true;
  }

  /**
   * Returns the hashcode of the url.
   * 
   * @return The hash code
   */
  @Override
  public int hashCode() {
    return this.url.hashCode();
  }

  /**
   * Returns the String representing this repository. The output will be in
   * fact the repository URL.
   * 
   * @return The repository URL
   */
  @Override
  public String toString() {
    return this.url.toExternalForm();
  }

  /**
   * Returns the name of the repository.
   * 
   * @return The name of this repository
   */
  public String getName() {
    return this.name;
  }

  /**
   * Returns the URL of this repository.
   * 
   * @return URL the url of this repository
   */
  public URL getURL() {
    return this.url;
  }
}