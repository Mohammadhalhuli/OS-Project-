
/**
 * The most used method
 * **/
public interface QueueInterface<E> {
    public int size_Q();
    public boolean isEmpty_Q();
    public void enqueue(E e);
    public E first_Q();
    public E dequeue_Q();//work in the queue rem first item or retu item 
}
