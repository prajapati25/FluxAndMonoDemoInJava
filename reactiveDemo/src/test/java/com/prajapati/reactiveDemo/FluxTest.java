package com.prajapati.reactiveDemo;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class FluxTest {
	Examples ex = new Examples();

	@Test
	void getFlux() {
		var flux = ex.getflux();

		StepVerifier.create(flux)
				// .expectNext("Hello", "World")
				.expectNext("Hello").expectNextCount(1).verifyComplete();
	}
	@Test
	void getflux_map() {
		var flux = ex.getflux_map();

		StepVerifier.create(flux)
				 .expectNext("HELLO", "WORLD")
				.verifyComplete();
	}
	@Test
	void getflux_filter() {
		var flux = ex.getflux_filter(3);

		StepVerifier.create(flux)
				 .expectNext("HELLO", "WORLD","SHAYAM")
				.verifyComplete();
	}
	@Test
	void getflux_flatMap() {
		var flux = ex.getflux_flatMap(5);

		StepVerifier.create(flux)
		 .expectNext("S","H","A","Y","A","M")
		//.expectNextCount(6)
				.verifyComplete();
	}
	@Test
	void getflux_async() {
		var flux = ex.getflux_async(5);

		StepVerifier.create(flux)
		// .expectNext("S","I","T","A","R","A","M")
		.expectNextCount(7)
				.verifyComplete();
	}
	
	@Test
	void getflux_concatMap() {
		var flux = ex.getflux_concatMap(5);

		StepVerifier.create(flux)
		 .expectNext("S","I","T","A","R","A","M")
		//.expectNextCount(6)
				.verifyComplete();
	}
	
	@Test
	void getflux_tranform() {
		var flux = ex.getflux_transform(5);

		StepVerifier.create(flux)
		 .expectNext("S","H","A","Y","A","M")
		//.expectNextCount(6)
				.verifyComplete();
	}

	@Test
	void getflux_tranform_1() {
		var flux = ex.getflux_transform(6);

		StepVerifier.create(flux)
		 .expectNext("Default")
		//.expectNextCount(6)
				.verifyComplete();
	}
	@Test
	void getflux_switchIfEmpty() {
		var flux = ex.getflux_switchIfEmpty(6);

		StepVerifier.create(flux)
		 .expectNext("D","E","F","A","U","L","T")
		//.expectNextCount(6)
				.verifyComplete();
	}
	
	@Test
	void flux_Concat() {
		var flux = ex.flux_Concat();

		StepVerifier.create(flux)
		 .expectNext("A","B","C","D","E","F")
		//.expectNextCount(6)
				.verifyComplete();
	}
	@Test
	void flux_ConcatWith() {
		var flux = ex.flux_ConcatWith();

		StepVerifier.create(flux)
		 .expectNext("A","B","C","D","E","F")
		//.expectNextCount(6)
				.verifyComplete();
	}
	@Test
	void flux_merge() {
		var flux = ex.flux_merge();

		StepVerifier.create(flux)
		 .expectNext("A","D","B","E","C","F")
		//.expectNextCount(6)
				.verifyComplete();
	}
	@Test
	void flux_mergeWith() {
		var flux = ex.flux_mergeWith();

		StepVerifier.create(flux)
		 .expectNext("A","D","B","E","C","F")
		//.expectNextCount(6)
				.verifyComplete();
	}
	@Test
	void mono_ConcatWith() {
		var flux = ex.mono_ConcatWith();

		StepVerifier.create(flux)
		 .expectNext("A","B")
		//.expectNextCount(6)
				.verifyComplete();
	}
	@Test
	void mono_mergeWith() {
		var flux = ex.mono_ConcatWith();

		StepVerifier.create(flux)
		 .expectNext("A","B")
		//.expectNextCount(6)
				.verifyComplete();
	}
	
	@Test
	void flux_mergeSequential() {
		var flux = ex.flux_mergeSequential();

		StepVerifier.create(flux)
		 .expectNext("A","B","C","D","E","F")
		//.expectNextCount(6)
				.verifyComplete();
	}
	@Test
	void flux_zip() {
		var flux = ex.flux_zip();

		StepVerifier.create(flux)
		 .expectNext("AD14","BE25","CF36")
		//.expectNextCount(6)
				.verifyComplete();
	}
	@Test
	void flux_zipWith() {
		var flux = ex.flux_zipWith();

		StepVerifier.create(flux)
		 .expectNext("AD","BE","CF")
		//.expectNextCount(6)
				.verifyComplete();
	}
}
