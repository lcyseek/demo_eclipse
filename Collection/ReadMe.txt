List  有序 可以重复，    访问集合中的元素可以根据元素的索引来访问。
	Iterator遍历List集合时，集合不能增删元素，因为迭代器依赖于集合，集合增删元素了，迭代器不知道。要通过迭代器增删元素，
		但是Iterator没有增删接口，这时候，List集合特有的迭代器ListIterator及可以使用了。

	ArayList（查询快，增删慢）
	    	特点：内部是通过数组实现的。当数组大小不满足时需要增加存储能力，就要讲已经有数组的数据复制到新的存储空间中。
	    	当从ArrayList的中间位置插入或者删除元素时，需要对数组进行复制、移动、代价比较高。因此，它适合随机查找和遍历，不适合插入和删除。
	
	Vector（线程安全）
			特点：Vector与ArrayList一样，也是通过数组实现的，不同的是它支持线程的同步
			
	LinkedList（查询慢，增删快）
			特点：LinkedList是用链表结构存储数据的，很适合数据的动态插入和删除，随机访问和遍历速度比较慢。
			
			
			
			
			
Set   无序 不可以重复，访问集合中的元素只能根据元素本身来访问（也是不能集合里元素不允许重复的原因）。

	HashSet
		 	特点：由哈希表支持（实际上是一个HashMap实例,由hashMap保证唯一）。无序！！
		 	
	LinkedHashSet 有序 
			特点：LinkedHashSet集合同样是根据元素的hashCode值来决定元素的存储位置，但是它同时使用链表维护元素的次序。
				这样使得元素看起 来像是以插入顺序保存的，也就是说，当遍历该集合时候，LinkedHashSet将会以元素的添加顺序访问集合的元素。有序！！
			
	TreeSet 无序！
			特点：有序（这个有序不是插入取出的顺序一致，而是真正的排序！）	
			
				
Map   
	HashMap：键是Set集合，所以，无序（存储和取出的顺序），唯一
	    集合中保存Key-value对形式的元素，访问时只能根据每项元素的key来访问其value。
	  Key唯一 value可重复
	  Map集合的数据结构针对键有效，跟值无关。
	    如果键存储的是自定义对象，就要重写hashCode和equals方法，来保证键的唯一性
	    键相同，值覆盖！！！！
	    
	LinkedHashSet：有序（链表保证有序），唯一。
	TreeMap:键排序（大小顺序，不是存入取出的顺讯）且唯一！


----------------------------------------

HashMap 和 HashSet 是 Java Collection Framework 的两个重要成员，其中 HashMap 是 Map 接口的常用实现类，HashSet 是 Set 接口的常用实现类。
虽然 HashMap 和 HashSet 实现的接口规范不同，但它们底层的 Hash 存储机制完全一样，甚至 HashSet 本身就采用 HashMap 来实现的。 
通过 HashMap、HashSet 的源代码分析其 Hash 存储机制

实际上，HashSet 和 HashMap 之间有很多相似之处，对于 HashSet 而言，系统采用 Hash 算法决定集合元素的存储位置，这样可以保证能快速存、取集合元素；
对于 HashMap 而言，系统 key-value 当成一个整体进行处理，系统总是根据 Hash 算法来计算 key-value 的存储位置，这样可以保证能快速存、取 Map 的 key-value 对。
实际上，当系统决定存储 HashMap 中的 key-value 对时，完全没有考虑 Entry 中的 value，仅仅只是根据 key 来计算并决定每个 Entry 的存储位置。这也说明了前面的结论：我们完全可以把 Map 集合中的 value 当成 key 的附属，当系统决定了 key 的存储位置之后，value 随之保存在那里即可。

HashTable/HashMap的区别 