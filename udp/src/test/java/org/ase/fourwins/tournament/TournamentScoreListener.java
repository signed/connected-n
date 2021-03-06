package org.ase.fourwins.tournament;

import static org.ase.fourwins.board.Board.Score.DRAW;
import static org.ase.fourwins.board.Board.Score.LOSE;
import static org.ase.fourwins.board.Board.Score.WIN;

import org.ase.fourwins.board.Board.Score;
import org.ase.fourwins.game.Game;
import org.ase.fourwins.game.Player;
import org.ase.fourwins.tournament.listener.TournamentListener;

import lombok.Getter;

public class TournamentScoreListener implements TournamentListener {

	private static final double FULL_POINT = 1;
	private static final double ZERO = 0.0;
	private static final double HALF_POINT = 0.5;
	@Getter
	private final ScoreSheet scoreSheet = new ScoreSheet();

	@Override
	public void gameEnded(Game game) {
		Object lastToken = game.gameState().getToken();
		Score score = game.gameState().getScore();
		if (score.equals(WIN)) {
			addPointForPlayer(game.getPlayerForToken(lastToken), FULL_POINT);
			game.getOpponentsForToken(lastToken).forEach(p -> addPointForPlayer(p, ZERO));
		} else if (score.equals(DRAW)) {
			addPointForPlayer(game.getPlayerForToken(lastToken), HALF_POINT);
			game.getOpponentsForToken(lastToken).forEach(p -> addPointForPlayer(p, HALF_POINT));
		} else if (score.equals(LOSE)) {
			game.getOpponentsForToken(lastToken).forEach(p -> addPointForPlayer(p, FULL_POINT));
			addPointForPlayer(game.getPlayerForToken(lastToken), ZERO);
		}
	}

	private void addPointForPlayer(Player player, double value) {
		scoreSheet.merge(player.getToken(), value, (i, j) -> i + value);
	}

	@Override
	public void seasonEnded() {
		System.out.println(scoreSheet);
	}
}
