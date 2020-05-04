package io.pivotal.literx;

import java.time.Duration;

import reactor.core.publisher.Flux;

/**
 * Learn how to create Flux instances.
 *
 * @author Sebastien Deleuze
 * @see <a href="https://projectreactor.io/docs/core/release/api/reactor/core/publisher/Flux.html">Flux Javadoc</a>
 */
public class Part01Flux {

//========================================================================================

	// TODO Return an empty Flux
	// Flux.empty()
	// Create a Flux that completes without emitting any item.
	Flux<String> emptyFlux() {
		return Flux.empty();
	}

//========================================================================================

	// TODO Return a Flux that contains 2 values "foo" and "bar" without using an array or a collection
	// just(T... data)
	// Create a Flux that emits the provided elements and then completes.
	Flux<String> fooBarFluxFromValues() {
		return Flux.just("foo", "bar");
	}

//========================================================================================

	// TODO Create a Flux from a List that contains 2 values "foo" and "bar"
	// fromArray(T[] array)
	// Create a Flux that emits the items contained in the provided array. 
	Flux<String> fooBarFluxFromList() {
		return Flux.fromArray(new String[]{"foo", "bar"});
	}

//========================================================================================

	// TODO Create a Flux that emits an IllegalStateException
	// error(Throwable error)
	// Create a Flux that terminates with the specified error immediately after being subscribed to.
	Flux<String> errorFlux() {
		return Flux.error(new IllegalStateException());
	}

//========================================================================================

	// TODO Create a Flux that emits increasing values from 0 to 9 each 100ms
	// interval(Duration period)
	// Create a Flux that emits long values starting with 0 and incrementing at specified time intervals on the global timer.
	// take(long n)
	// Take only the first N values from this Flux, if available.
	Flux<Long> counter() {
		return Flux.interval(Duration.ofMillis(100))
				.take(10);
	}

}
