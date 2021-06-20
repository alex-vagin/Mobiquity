package com.mobiquity.packer;

import com.mobiquity.exception.APIException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PackerTest {
	@Test
	public void pack() throws IOException, APIException {
		Path path = Paths.get("src", "main", "test", "resources", "example_input");
		String pack = Packer.pack(path.toString());
		Path path_output = Paths.get("src", "main", "test", "resources", "example_output");
		try (Stream<String> lines = Files.lines(path_output)) {
			Assertions.assertEquals(lines.collect(Collectors.joining( "\n")), pack);
		}
	}
	
	@Test
	public void testExceptionWithoutColon() {
		Path path = Paths.get("src", "main", "test", "resources", "input_without_colon");
		Assertions.assertThrows(APIException.class, () -> Packer.pack(path.toString()));
	}
	
	@Test
	public void testExceptionWithoutComma() {
		Path path = Paths.get("src", "main", "test", "resources", "input_without_comma");
		Assertions.assertThrows(APIException.class, () -> Packer.pack(path.toString()));
	}
	
	@Test
	public void testNumberFormat() {
		Path path = Paths.get("src", "main", "test", "resources", "input_number_format");
		Assertions.assertThrows(APIException.class, () -> Packer.pack(path.toString()));
	}
}