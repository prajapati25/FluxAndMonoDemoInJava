package com.prajapati.reactiveDemo;

import java.util.List;

import org.junit.jupiter.api.Test;

import reactor.test.StepVerifier;

public class MonoTest {
	Examples ex = new Examples();

	@Test
	void getMono_flatMap() {
		var mono = ex.getMono_flatMap();

		StepVerifier.create(mono).expectNext(List.of("H", "i", "i"))
				// .expectNextCount(6)
				.verifyComplete();
	}

	@Test
	void getMono_flatMapMany() {
		var flux = ex.getMono_flatMapMany();

		StepVerifier.create(flux).expectNext("H", "i", "i")
				// .expectNextCount(6)
				.verifyComplete();
	}
	@Test
	void mono_zipWith() {
		var mono = ex.mono_zipWith();

		StepVerifier.create(mono)
		 .expectNext("AB")
		//.expectNextCount(6)
				.verifyComplete();
	}

}
