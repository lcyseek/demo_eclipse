Collection接口是构造类集框架的基础
	boolean add(E e)：向集合中增加元素，如果该类型集合允许有重复元素（如：ArrayList、LinkedList等）或者不允许有重复元素但新添加的元素不在集合中则返回true，如果该类型集合不允许有重复元素并且新添加的元素已经在集合中则返回false。
	boolean addAll(Collection<? extendsE> c)：将一个类集c中的所有元素添加到另一个类集（本类集）。
	void clear()：清除本类集中所有元素，调用完该方法后本类集将为空。
	boolean contains(Object o)：如果本类集包含元素e，并且满足(o==null ?e==null : o.equals(e))则返回true，否则返回false
	boolean containsAll(Collection<?> c)：如果本类集包含指定类集c中的所有元素则返回true，否则返回false。
	boolean equals(Object o)：如果本类集的equals方法是我们自己重写的，则具体两个类集怎样才算equal（是引用相等还是值相等且顺序相同），我们自己决定。如果类集的equals方法不是我们重写的，则当两个类集类型为List（包括ArrayList和LinkedList，元素可以重复）时，必须两个类集的元素个数相等，元素顺序相同，且相同位置的元素对应相等才返回true，否则返回false；当两个类集类型为Set（包括HashSet和TreeSet，元素不能重复）时，只须两个类集的元素个数相等，并且一个类集中的任一个元素在另一个类集中都能找到相同的元素（不必顺序相同，因为Set是无序的），则返回true，否则返回false。
	int hashCode()：返回本类集的hash code值，具体算法依赖于类集中元素的值和类型
	boolean isEmpty()：若本类集没有元素则返回true，否则返回false。
	Iterator<E> iterator()：返回本类集中所有元素的迭代函数
	boolean remove(Object o)：若本类集中有值与o的值相等的元素，则删除该元素，并返回true，若没有则返回false。当类集类型为List时，有可能有多个元素与o的值相等，此时，只会删除第一个值为o的元素，其他元素位置不变。
	boolean removeAll(Collection<?>c)：删除本类集中与指定类集c中的元素相等的所有元素，不管有多少个元素，只要在c中出现了，调用后d中就不会出现。若调用后本类集的元素有改变（即有元素被删除），则返回true，否则返回false。
	boolean retainAll(Collection<?>c)：保持本类集中在指定类集中出现过的所有元素，即删除本类集中不包含在指定类集c中的所有元素。若调用后本类集的元素有改变（即有元素被删除），则返回true，否则返回false。
	int size()：返回本类集中元素的个数
	Object[] toArray()：返回一个包含了本类集中所有元素的数组，数组类型为：Object[]，因为底层是通过iterator()方法来传值的，所以数组中元素的顺序同样依赖于本类集的类型，若为List则按List的顺序放入数组，若为Set则无序
	<T>T[] toArray(T[] a)：以泛型的形式来传递返回数组的类型
	
	


迭代器迭代时，不可以添加元素:
逻辑上讲，迭代时可以添加元素，但是一旦开放这个功能，很有可能造成很多意想不到的情况。 
比如你在迭代一个ArrayList，迭代器的工作方式是依次返回给你第0个元素，第1个元素，等等，假设当你迭代到第5个元素的时候，你突然在ArrayList的头部插入了一个元素，使得你所有的元素都往后移动，于是你当前访问的第5个元素就会被重复访问。 
java认为在迭代过程中，容器应当保持不变。因此，java容器中通常保留了一个域称为modCount，每次你对容器修改，这个值就会加1。当你调用iterator方法时，返回的迭代器会记住当前的modCount，随后迭代过程中会检查这个值，一旦发现这个值发生变化，就说明你对容器做了修改，就会抛异常。 
	     