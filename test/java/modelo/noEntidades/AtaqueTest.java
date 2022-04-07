package modelo.noEntidades;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class AtaqueTest {
	@Test
	void checkSiCrearAtaqueFunciona() {
		assertDoesNotThrow(
				() -> new Ataque("af_ad_1")
		);
	}
}