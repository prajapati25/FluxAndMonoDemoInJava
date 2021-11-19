package com.prajapati.reactiveDemo;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Examples {
	public Flux<String> getflux() {

		return Flux.fromIterable(List.of("Hello", "World"))
				.log();
	}

	public Flux<String> getflux_map() {

		return Flux.fromIterable(List.of("Hello", "World"))
				.map(String::toUpperCase)
				.log();
	}

	public Flux<String> getflux_filter(int length) {

		return Flux.fromIterable(List.of("Hello", "World", "ram", "shayam"))
				.filter(s -> s.length() > length)
				.map(String::toUpperCase)
				.log();
	}

	public Flux<String> getflux_flatMap(int length) {

		return Flux.fromIterable(List.of("Hello", "World", "ram", "shayam"))
				.filter(s -> s.length() > length)
				.map(String::toUpperCase)
				.flatMap(s -> splitString(s))
				.log();
	}
	public Flux<String> getflux_transform(int length) {

		Function<Flux<String>,Flux<String>>  filtermap = name->
		            name.filter(s->s.length()>length)
		            .map(String::toUpperCase)
		            .flatMap(s -> splitString(s));
		            
		return Flux.fromIterable(List.of("Hello", "World", "ram", "shayam")).
				transform(filtermap)
				.defaultIfEmpty("Default");
	}
	public Flux<String> getflux_switchIfEmpty(int length) {

		Function<Flux<String>,Flux<String>>  filtermap = name->
		 		name
				.map(String::toUpperCase)
				.filter(s->s.length()>length)
				.flatMap(s->splitString(s));
		
		var defaultFlux = Flux.just("Default")
				.transform(filtermap);
		
		return Flux.fromIterable(List.of("Hello", "World", "ram", "shayam"))
				.transform(filtermap)
				.switchIfEmpty(defaultFlux)
				.log();
	}


	public Flux<String> getflux_async(int length) {

		return Flux.fromIterable(List.of("Hello", "Sita", "ram", "shayam"))
				.filter(s -> s.length() < length)
				.map(String::toUpperCase)
				.flatMap(s -> splitStringDelay(s))
				.log();
	}

	public Flux<String> getflux_concatMap(int length) {

		return Flux.fromIterable(List.of("Hello", "Sita", "ram", "shayam"))
				.filter(s -> s.length() < length)
				.map(String::toUpperCase)
				.concatMap(s -> splitStringDelay(s))
				.log();
	}

	public Flux<String> splitString(String sname) {
		var charArray = sname.split("", 0);
		return Flux.fromArray(charArray);
	}
	
	public Flux<String>  flux_Concat(){
		var fluxA= Flux.just("A","B","C");
		var fluxB= Flux.just("D","E","F");
		return Flux.concat(fluxA,fluxB);
		
	}
	public Flux<String>  flux_merge(){
		var fluxA= Flux.just("A","B","C")
				.delayElements(Duration.ofMillis(100));
		var fluxB= Flux.just("D","E","F")
				.delayElements(Duration.ofMillis(125));
		return Flux.merge(fluxA,fluxB);
		
	}
	public Flux<String>  flux_mergeSequential(){
		var fluxA= Flux.just("A","B","C")
				.delayElements(Duration.ofMillis(100));
		var fluxB= Flux.just("D","E","F")
				.delayElements(Duration.ofMillis(125));
		return Flux.mergeSequential(fluxA,fluxB);
		
	}
	public Flux<String>  flux_mergeWith(){
		var fluxA= Flux.just("A","B","C")
				.delayElements(Duration.ofMillis(100));
		var fluxB= Flux.just("D","E","F")
				.delayElements(Duration.ofMillis(125));
		return fluxA.mergeWith(fluxB);
		
	}
	public Flux<String>  flux_ConcatWith(){
		var fluxA= Flux.just("A","B","C");
		var fluxB= Flux.just("D","E","F");
		return fluxA.concatWith(fluxB);
		
	}
	public Flux<String>  flux_zip(){
		var fluxA= Flux.just("A","B","C");
		var fluxB= Flux.just("D","E","F");
		var fluxC= Flux.just("1","2","3");
		var fluxD= Flux.just("4","5","6");
		return Flux.zip(fluxA, fluxB,fluxC,fluxD)
				.map(t->t.getT1()+t.getT2()+t.getT3()+t.getT4());
		
	}
	public Flux<String>  flux_zipWith(){
		var fluxA= Flux.just("A","B","C");
		var fluxB= Flux.just("D","E","F");
		return fluxA.zipWith(fluxB,(first,second)->first+second);		
	}

	public Flux<String> splitStringDelay(String sname) {
		var charArray = sname.split("", 0);
		var delay = new Random().nextInt(1000);
		System.out.println(delay);
		return Flux.fromArray(charArray)
				.delayElements(Duration.ofMillis(delay));
	}

	public Mono<String> getMono() {

		return Mono.just("Hi").log();
	}
	public Flux<String> mono_ConcatWith() {
	
		var monoA= Mono.just("A");
		var monoB= Mono.just("B");
		return monoA.concatWith(monoB);
	}
	public Flux<String> mono_mergeWith() {
		
		var monoA= Mono.just("A");
		var monoB= Mono.just("B");
		return monoA.mergeWith(monoB);
	}
	
    public Mono<String> mono_zipWith() {
		
		var monoA= Mono.just("A");
		var monoB= Mono.just("B");
		return monoA.zipWith(monoB)
				.map(t->t.getT1()+t.getT2());
	}


	public Mono<List<String>> getMono_flatMap() {

		return Mono.just("Hii").flatMap(s -> splitStringMono(s)).log();
	}

	public Flux<String> getMono_flatMapMany() {

		return Mono.just("Hii").flatMapMany(s -> splitString(s)).log();
	}

	public Mono<List<String>> splitStringMono(String sname) {
		var charArray = sname.split("", 0);
		return Mono.just(List.of(charArray));
	}

	public static void main(String[] args) {

		Examples e = new Examples();
		
		 e.getflux().subscribe(s -> System.out.println(s)); e.getMono().subscribe(s ->
		 System.out.println(s));
		 

	}

	

}
