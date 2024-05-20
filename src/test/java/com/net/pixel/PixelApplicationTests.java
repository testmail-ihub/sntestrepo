package com.net.pixel;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Collectors;

@SpringBootTest
@Slf4j
class PixelApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void print() throws IOException {
		Resource resource = new ClassPathResource("test.txt");
		File file = resource.getFile();
		BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

		String[] array = bufferedReader.lines().collect(Collectors.joining()).split(",");
		for(String s: array){
			log.info(s);
			log.info(s);
		}
	}

}
