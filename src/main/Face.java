package main;

public class Face {
	private Segment seg1;
	private Segment seg2;
	private Segment seg3;
	/**
	 * @param seg1
	 * @param seg2
	 * @param seg3
	 */
	public Face(Segment seg1, Segment seg2, Segment seg3) {
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
	 * @return the seg2
	 */
	public Segment getSeg2() {
		return seg2;
	}
	/**
	 * @return the seg3
	 */
	public Segment getSeg3() {
		return seg3;
	}
}
