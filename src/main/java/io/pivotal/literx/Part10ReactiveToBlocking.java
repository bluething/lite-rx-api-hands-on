package io.pivotal.literx;

import io.pivotal.literx.domain.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Learn how to turn Reactive API to blocking one.
 *
 * @author Sebastien Deleuze
 */
public class Part10ReactiveToBlocking {

//========================================================================================

	// TODO Return the user contained in that Mono
	// Mono.block()
	// Subscribe to this Mono and block indefinitely until a next signal is received.
	// Returns that value, or null if the Mono completes empty.
	// In case the Mono errors, the original exception is thrown (wrapped in a RuntimeException if it was a checked exception).
	User monoToValue(Mono<User> mono) {
		return mono.block();
	}

//========================================================================================

	// TODO Return the users contained in that Flux
	// Flux.toIterable()
	// Transform this Flux into a lazy Iterable blocking on Iterator.next() calls.
	Iterable<User> fluxToValues(Flux<User> flux) {
		return flux.toIterable();
	}

}
