package org.ase.fourwins.game;

import org.ase.fourwins.board.BoardInfo;

import lombok.Getter;

public abstract class Player {

	@Getter
	private String token;

	public Player(String token) {
		this.token = token;
	}

	protected abstract int nextColumn();

	public boolean joinGame(String opposite, BoardInfo boardInfo) {
		return true;
	}

	protected void tokenWasInserted(String token, int column) {
	}

}