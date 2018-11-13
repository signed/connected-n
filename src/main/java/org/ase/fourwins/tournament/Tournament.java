package org.ase.fourwins.tournament;

import java.util.stream.Stream;

import org.ase.fourwins.board.Board.GameState;
import org.ase.fourwins.game.Player;

public interface Tournament {

	public interface RegistrationResult {
		boolean isOk();
	}

	Stream<GameState> playSeason();

	RegistrationResult registerPlayer(Player player) ;

	Tournament deregisterPlayer(Player player);

}