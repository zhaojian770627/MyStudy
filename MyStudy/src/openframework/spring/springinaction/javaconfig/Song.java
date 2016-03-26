// <start id="song_java" />
package openframework.spring.springinaction.javaconfig;

public class Song {
  private final String title;
  private final String composer;
  private final String lyrics;

  public Song(String title, String composer, String lyrics) {
    this.title = title;
    this.composer = composer;
    this.lyrics = lyrics;
  }

  public String getTitle() {
    return title;
  }

  public String getComposer() {
    return composer;
  }

  public String getLyrics() {
    return lyrics;
  }
}
// <end id="song_java" />
