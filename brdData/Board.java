package brdData;

import java.util.*;

public class Board {
    public int check(int loc) {
        switch (loc) {
            case 5:
                return 26;
            case 13:
                return 46;
            case 18:
                return 39;
            case 37:
                return 62;
            case 48:
                return 72;
            case 60:
                return 82;
            case 65:
                return 95;
            case 23:
                return 7;
            case 33:
                return 9;
            case 44:
                return 14;
            case 68:
                return 16;
            case 77:
                return 41;
            case 94:
                return 51;
            case 99:
                return 28;
            default:
                return -1;
        }
    }
    public int isSL(int i, int x) {
    	// i = player location
    	// x = result of check, i.e final position after snake or ladder
    	if(x==-1) {
    		return -1;
    	}
    	if(i>x) {
    		return 1;   // if current location is bigger than final then it is a snake
    	} else {
    		return 2;   // else it is a ladder
    	}
    }
}
