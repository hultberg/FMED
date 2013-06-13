/**
 * Følg med eller drikk! by Edvin Hultberg is licensed under a Creative Commons Attribution 3.0 Norway License.
 * Read the license here:
 * http://creativecommons.org/licenses/by/3.0/no/
 * 
 * Enjoy, its in the public domain with the exception of the attribution part.
 * 
 * @author Hultberg
 * @since 0.1
 * @version 1.3.2
 */

public class d {

	private boolean alive;
	private a action;
	private b entitytype;	
	private int health;
	
	public d(b arg0){
		this.entitytype = arg0;
		
		if(arg0 == b.PLAYER)
			this.health = 3;
	}
	
	public boolean isAlive() {
		return alive;
	}
	
	public a getAction() {
		return action;
	}
	
	public b getEntitytype() {
		return entitytype;
	}
	
	public int getHealth() {
		return health;
	}
	
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	
	public void setAction(a action) {
		this.action = action;
	}
	
	public void setEntitytype(b entitytype) {
		this.entitytype = entitytype;
	}
	
	public void setHealth(int health) {
		this.health = health;
	}	
	
}
