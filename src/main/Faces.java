package main;

public class Faces {
	private Segment seg1;
	private Segment seg2;
	private Segment seg3;
	/**
	 * @param seg1
	 * @param seg2
	 * @param seg3
	 */
	public Faces(Segment seg1, Segment seg2, Segment seg3) {
		super();
		this.seg1 = seg1;
		this.seg2 = seg2;
		this.seg3 = seg3;
	}
	/**
	 * @return the seg1
	 */
	public Segment getSeg1() {
		return seg1;
	}
	/**
	 * @param seg1 the seg1 to set
	 */
	public void setSeg1(Segment seg1) {
		this.seg1 = seg1;
	}
	/**
	 * @return the seg2
	 */
	public Segment getSeg2() {
		return seg2;
	}
	/**
	 * @param seg2 the seg2 to set
	 */
	public void setSeg2(Segment seg2) {
		this.seg2 = seg2;
	}
	/**
	 * @return the seg3
	 */
	public Segment getSeg3() {
		return seg3;
	}
	/**
	 * @param seg3 the seg3 to set
	 */
	public void setSeg3(Segment seg3) {
		this.seg3 = seg3;
	}
	
	
}
