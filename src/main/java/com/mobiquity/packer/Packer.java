package com.mobiquity.packer;

import com.mobiquity.exception.APIException;
import com.mobiquity.model.Pack;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.mobiquity.model.UtilException.rethrowFunction;

public class Packer {
  private Packer() {
  }

  public static String pack(String filePath) throws APIException {
    try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
      return lines.map(rethrowFunction(Pack::new)).map(Pack::calculate).collect(Collectors.joining( "\n"));
    } catch (IOException e) {
      throw new APIException(e.getMessage());
    }
  }
}
