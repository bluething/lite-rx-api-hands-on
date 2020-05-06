package io.pivotal.literx;

import io.pivotal.literx.domain.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Learn how to merge flux.
 *
 * @author Sebastien Deleuze
 */
public class Part05Merge {

//========================================================================================

	// TODO Merge flux1 and flux2 values with interleave
	// mergeWith(Publisher<? extends T> other)
	// Merge data from this Flux and a Publisher into an interleaved merged sequence.
	Flux<User> mergeFluxWithInterleave(Flux<User> flux1, Flux<User> flux2) {
		return flux1.mergeWith(flux2);
	}

//========================================================================================

	// TODO Merge flux1 and flux2 values with no interleave (flux1 values and then flux2 values)
	// concatWith(Publisher<? extends T> other)
	// Concatenate emissions of this Flux with the provided Publisher (no interleave). 
	Flux<User> mergeFluxWithNoInterleave(Flux<User> flux1, Flux<User> flux2) {
		return flux1.concatWith(flux2);
	}

//========================================================================================

	// TODO Create a Flux containing the value of mono1 then the value of mono2// concat(Iterable<? extends Publisher<? extends T>> sources)
	// Concatenate all sources provided in an Iterable, forwarding elements emitted by the sources downstream.
	// Concatenation is achieved by sequentially subscribing to the first source then waiting for it to complete before subscribing to the next, and so on until the last source completes.
	Flux<User> createFluxFromMultipleMono(Mono<User> mono1, Mono<User> mono2) {
		return Flux.concat(mono1, mono2);
	}

}
