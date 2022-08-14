import org.junit.Test;

public class week3_SLList {

	public IntNode first;

	public void addFirst(int x) {
		first = new IntNode(x, first);
	}

	/** Q1 */
	public void insert(int item, int position) {
		insert_helper(item, position, first);
	}

	private void insert_helper(int item, int position, IntNode currentNode){
		if (position == 0 || currentNode == null) {
			currentNode = new IntNode(item, currentNode);
		} else {
			position -= 1;
			insert_helper(item, position, currentNode.next);
		}
	}

	/** Q2 no new 完全没想出来
	 * 每次均在first 和 rest之间，即在脖子上插入
	 * 所以每次需要将第一个和后面分开
	 */
	public void reverse() {
		IntNode frontOfReversed = null;
		IntNode nextNodeToAdd = first;
		while (nextNodeToAdd != null) {
			IntNode remainderOfOriginal = nextNodeToAdd.next;
			nextNodeToAdd.next = frontOfReversed;
			frontOfReversed = nextNodeToAdd;
			nextNodeToAdd = remainderOfOriginal;
		}
		first = frontOfReversed;
	}

	/** Q3 很抽象 */
	@Test
	public void reverseRecur() {
		first = reverseRecur_helper(first);
	}

	private IntNode reverseRecur_helper(IntNode front) {
		if (front == null || front.next == null) {
			return front;
		} else {
			IntNode reversed = reverseRecur_helper(front.next);
			front.next.next = front;
			front.next = null;
			return reversed;
		}
	}

	/**  Q4 */
	public static int[] insert(int[] arr, int item, int position) {
		int[] newLst = new int[arr.length + 1];
		if (position >= arr.length) {
			System.arraycopy(arr, 0, newLst, 0, arr.length);
			newLst[arr.length] = item;
		} else {
			System.arraycopy(arr, 0, newLst, 0, position);
			newLst[position] = item;
			System.arraycopy(arr, position, newLst, position + 1, arr.length - position)
		}
		return newLst;
	}

	/** Q5 */
	public static void reverse(int[] arr) {
		int len = arr.length;
		for (int i = 0; i < len / 2; i += 1) {
			int temp = arr[i];
			arr[i] = arr[len - i - 1];
			arr[len - 1 - i] = temp;
		}
	}

	/** Q6 */
	public static int[] replicate(int[] arr) {
		int newLen = 0;
		for (int i = 0; i < arr.length; i += 1) {
			newLen += arr[i];
		}
		int[] newLst = new int[newLen];
		int index = 0;
		int temp = 0;
		for (int i = 0; i < arr.length; i += 1) {
			temp = arr[i];
			while (temp > 0) {
				newLst[index] = arr[i];
				index += 1;
				temp -= 1;
			}
		}
		return newLst;
	}

}