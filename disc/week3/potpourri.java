public class potpourri{
	public static int[] flatten(int[][] x) {
		int totalLength = 0;

		for (int[] temp: x) {
			totalLength += temp.length;
		}

		int[] a = new int[totalLength];
		int aIndex = 0;

		for (int[] temp: x) {
			for (int currentLength = 0; currentLength < temp.length; currentLength += 1) {
				a[aIndex] = temp[currentLength];
				aIndex += 1;
			}
		}
		return a;
	}

	public void skippify() {
		IntList p = this;
		int n = 1;
		while (p != null) {
			IntList next = p.rest;
			for (int i = 0; i < n; i += 1) {
				if (next == null) {
					return;
				}
				next = next.rest;
			}
			n += 1;
			p.nest = next;
			p = p.nest;
		}
	}
	/** 删除链表中的元素常用前后双指针 */
	public static void removeDuplicates(IntList p) {
		if (p == null) {
			return;
		}
		IntList current = p.rest;
		IntList previous = p;
		while (current != null) {
			if(current.first != previous.first) {
				previous = current;
			} else {
				previous.rest = current.rest;
			}
			current = current.rest;
		}
	}

}
