package com.junior.technical.lawn;

public class LawnSize extends LawnVector{
	private LawnSize(int x, int y) {
	    super(x, y);
	}
	
	public static LawnSize of(int x, int y) {
	    return new LawnSize(x, y);
	}
}
