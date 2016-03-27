package openframework.spring.springinaction.xmlaop;

public interface MindReader {
  void interceptThoughts(String thoughts);

  String getThoughts();
}