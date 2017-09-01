
class CryptoQueue<T>
{

    private Node<T> head, tail;
    private int size;

    CryptoQueue()
    {
        head = new Node<>(null);
        tail = new Node<>(null);
        head.setNext(tail);
        tail.setPrev(head);
        size = 0;
    }

    synchronized void add(T t)
    {
        Node<T> n = new Node<>(t);
        n.setNext(tail);
        n.setPrev(tail.getPrev());
        tail.getPrev().setNext(n);
        tail.setPrev(n);
        size++;
    }

    synchronized T poll()
    {
        if(size<1) return null;

        Node<T> n = head.getNext();
        T t = n.remove();
        size--;
        return t;
    }

    int size()
    {
        return size;
    }

    boolean isEmpty()
    {
        return size<1;
    }

    private class Node<T>
    {
        private Node<T> next, prev;
        private T data;

        Node(T t)
        {
            next = null;
            prev = null;
            data = t;
        }

        T remove()
        {
            if(data==null) System.out.println("ERRRROROROROROOR§§§ SIZE: "+size);
            next.setPrev(prev);
            prev.setNext(next);
            return data;
        }

        Node<T> getNext() {return next;}
        void setNext(Node<T> n) {next = n;}
        Node<T> getPrev() {return prev;}
        void setPrev(Node<T> prev) {this.prev = prev;}
    }
}
