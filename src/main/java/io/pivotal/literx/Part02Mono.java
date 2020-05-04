package io.pivotal.literx;

import reactor.core.publisher.Mono;

/**
 * Learn how to create Mono instances.
 *
 * @author Sebastien Deleuze
 * @see <a href="https://projectreactor.io/docs/core/release/api/reactor/core/publisher/Mono.html">Mono Javadoc</a>
 */
public class Part02Mono {

//========================================================================================

	// TODO Return an empty Mono
	// empty()
	// Create a Mono that completes without emitting any item.
	Mono<String> emptyMono() {
		return Mono.empty();
	}

//========================================================================================

	// TODO Return a Mono that never emits any signal
	// never()
	// Return a Mono that will never signal any data, error or completion signal, essentially running indefinitely.
	Mono<String> monoWithNoSignal() {
		return Mono.never();
	}

//========================================================================================

	// TODO Return a Mono that contains a "foo" value
	// just(T data)
	// Create a new Mono that emits the specified item, which is captured at instantiation time
	Mono<String> fooMono() {
		return Mono.just("foo");
	}

//========================================================================================

	// TODO Create a Mono that emits an IllegalStateException
	// error(Throwable error)
	// Create a Mono that terminates with the specified error immediately after being subscribed to.
	Mono<String> errorMono() {
		return Mono.error(new IllegalStateException());
	}

}
