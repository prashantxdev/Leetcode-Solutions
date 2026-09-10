/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {

    public ListNode insertGreatestCommonDivisors(ListNode head) {

        ListNode current = head;

        while (current != null && current.next != null) {

            int a = current.val;
            int b = current.next.val;

            int gcd = findGCD(a, b);

            ListNode gcdNode = new ListNode(gcd);

            gcdNode.next = current.next;
            current.next = gcdNode;

            current = gcdNode.next;
        }

        return head;
    }

    private int findGCD(int a, int b) {

        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }

        return a;
    }
}