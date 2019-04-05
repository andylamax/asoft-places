The Kotlin Multiplatform Date Library
------
This library exposes the js `Date` and the jvm `LocalDateTime` (in jdk 8) so that the basics api for those dates can be used from a common module

In the common module, one can just do the following  
<pre><code>val d : Date = DateFactory.today()
val year = d.year
val month = d.month
val day = d.day
</code></pre>
-----
