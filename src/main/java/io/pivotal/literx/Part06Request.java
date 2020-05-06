package io.pivotal.literx;

import io.pivotal.literx.domain.User;
import io.pivotal.literx.repository.ReactiveRepository;
import io.pivotal.literx.repository.ReactiveUserRepository;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

/**
 * Learn how to control the demand.
 *
 * @author Sebastien Deleuze
 */
public class Part06Request {

	ReactiveRepository<User> repository = new ReactiveUserRepository();

//========================================================================================

	// TODO Create a StepVerifier that initially requests all values and expect 4 values to be received
	// expectNextCount(long count)
	// Expect to received count elements, starting from the previous expectation or onSubscribe.
	// expectComplete()
	// Expect the completion signal.
	StepVerifier requestAllExpectFour(Flux<User> flux) {
		return StepVerifier
				.create(flux)
				.expectNextCount(4)
				.expectComplete();
	}

//========================================================================================

	// TODO Create a StepVerifier that initially requests 1 value and expects User.SKYLER then requests another value and expects User.JESSE.
	// expectNext(T t)
	// Expect the next element received to be equal to the given value.
	// thenRequest(long n)
	// Request the given amount of elements from the upstream Publisher.
	// thenCancel()
	// Cancel the underlying subscription. This happens sequentially after the previous step. 
	StepVerifier requestOneExpectSkylerThenRequestOneExpectJesse(Flux<User> flux) {
		return StepVerifier
				.create(flux)
				.expectNext(User.SKYLER)
				.thenRequest(1)
				.expectNext(User.JESSE)
				.thenCancel();
	}

//========================================================================================

	// TODO Return a Flux with all users stored in the repository that prints automatically logs for all Reactive Streams signals
	// log()
	// Observe all Reactive Streams signals and trace them using Logger support.
	Flux<User> fluxWithLog() {
		return repository
				.findAll()
				.log();
	}

//========================================================================================

	// TODO Return a Flux with all users stored in the repository that prints "Starring:" on subscribe, "firstname lastname" for all values and "The end!" on complete
	// doOnSubscribe(Consumer<? super Subscription> onSubscribe)
	// Add behavior (side-effect) triggered when the Flux is done being subscribed, that is to say when a Subscription has been produced by the Publisher and passed to the Subscriber.onSubscribe(Subscription).
	// doOnNext(Consumer<? super T> onNext)
	// Add behavior (side-effect) triggered when the Flux emits an item.
	// doOnComplete(Runnable onComplete)
	// Add behavior (side-effect) triggered when the Flux completes successfully.
	Flux<User> fluxWithDoOnPrintln() {
		return repository
				.findAll()
				.doOnSubscribe(u -> System.out.println("Starring:"))
				.doOnNext(u -> System.out.println(u.getFirstname() + " " + u.getLastname()))
				.doOnComplete(() -> System.out.println("The end!"));
	}

}
