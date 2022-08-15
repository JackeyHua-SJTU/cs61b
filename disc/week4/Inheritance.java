/** Question 1 Playing with Puppers
 *  Compile-Error
 *  Compile-Error
 *  D
 *  E
 *  C
 *  B
 *  C
 *  Compile_Error(Wrong) -> C.Because Corgi is a Dog, so it passes the compiler check, 
 *  						and only runs the overriden(Corresponding) method
 */ 

 /** Question 2 Cast the Line
  *  Compile_Error
  *  success
  *  Compile_Error
  *  Compile_Error
  *  success
  *  success
  *  success(Wrong) -> RunTime Error. Because Dog is an Animal, but the reverse 
  *					   is incorrect
  *  RunTime_Error(Wrong) -> Compile_Error. Guess that two unrelated thing cannot be 
  *					         casted
  *  RunTime_Error -> Compile_Error
  */

import java.util.NoSuchElementException;

public class SLListVista extends SLList {
	public int indexOf(int x) {
		int ans = super.indexOf(x);
		if (ans == -1) {
			throw new NoSuchElementException();
		}
		return ans;
	}
}

public class DMSList {
	private IntNode sentinel;
	public DMSList() {
		sentinel = new IntNode(-1000, new newList(-1));
	}
	public class IntNode {
		public int item;
		public IntNode next;
		public IntNode(int i, IntNode h) {
			item = i;
			next = h;
		}
		public int max() {
			return Math.max(item, next.max());
		}
	}

	public class newList extends IntNode {
		public newList(int x) {
			super(x, null);
		}

		@Override
		public int max() {
			int max = item;
			IntNode temp = next;
			while (temp != null) {
				if (temp.item > max) {
					max = temp.item;
				}
				temp = temp.next;
			}
			return max;
		}
	}

	public int max() {
		return sentinel.next.max();
	}

}