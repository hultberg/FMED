/**
 * Følg med eller drikk! by Edvin Hultberg is licensed under a Creative Commons Attribution 3.0 Norway License.
 * Read the license here:
 * http://creativecommons.org/licenses/by/3.0/no/
 * 
 * Enjoy, its in the public domain with the exception of the attribution part.
 * 
 * @author Hultberg
 * @since 0.2b
 * @version 1.3.3
 */
public enum e {
	
	EASY(1000, 1015, 40, 10, 25, 100, 500, 800, "Lett"),
	MIDDLE(1000, 1040, 40, 10, 22, 300, 800, 2000, "Middels"),
	HARD(1000, 1050, 60, 7, 14, 500, 9000, 2500, "Vansklig"),
	EXPERT(1000, 1100, 70, 2, 10, 1000, 2000, 4000, "Ekspert"),
	INSANE(1000, 1100, 100, 2, 5, 2000, 400, 8000, "Mareritt");
	
	private int fromRandom;
	private int toRandom;
	private int randStop;
	private int fromWait;
	private int toWait;
	private int multi1;
	private int multi2;
	private int multi3;
	private String name;
	
	e(int fromR, int toR, int randSt, int fromW, int toW, int m1, int m2, int m3, String arg0){
		this.fromRandom = fromR;
		this.toRandom = toR;
		this.randStop = randSt;
		this.fromWait = fromW;
		this.toWait = toW;
		this.multi1 = m1;
		this.multi2 = m2;
		this.multi3 = m3;
		this.name = arg0;
	}	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRandStop() {
		return randStop;
	}

	public void setRandStop(int randStop) {
		this.randStop = randStop;
	}

	public int getFromRandom() {
		return fromRandom;
	}

	public int getToRandom() {
		return toRandom;
	}

	public int getFromWait() {
		return fromWait;
	}

	public int getToWait() {
		return toWait;
	}

	public int getMulti1() {
		return multi1;
	}

	public int getMulti2() {
		return multi2;
	}

	public int getMulti3() {
		return multi3;
	}

	public void setFromRandom(int fromRandom) {
		this.fromRandom = fromRandom;
	}

	public void setToRandom(int toRandom) {
		this.toRandom = toRandom;
	}

	public void setFromWait(int fromWait) {
		this.fromWait = fromWait;
	}

	public void setToWait(int toWait) {
		this.toWait = toWait;
	}

	public void setMulti1(int multi1) {
		this.multi1 = multi1;
	}

	public void setMulti2(int multi2) {
		this.multi2 = multi2;
	}

	public void setMulti3(int multi3) {
		this.multi3 = multi3;
	}
	
}
