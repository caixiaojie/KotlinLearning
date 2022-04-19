Collection KTX
Collection 扩展程序包含在 Android 的节省内存的集合库中使用的实用函数，包括 ArrayMap、LongSparseArray、LruCache 等等。

要使用此模块，请将以下内容添加到应用的 build.gradle 文件中：

dependencies {
implementation "androidx.collection:collection-ktx:1.2.0"
}
Collection 扩展程序利用 Kotlin 的运算符重载简化集合串联等操作，如以下示例所示：


// Combine 2 ArraySets into 1.
val combinedArraySet = arraySetOf(1, 2, 3) + arraySetOf(4, 5, 6)

// Combine with numbers to create a new sets.
val newArraySet = combinedArraySet + 7 + 8

Fragment KTX
Fragment KTX 模块提供了一系列扩展程序以简化 Fragment API。

要使用此模块，请将以下内容添加到应用的 build.gradle 文件中：

dependencies {
implementation "androidx.fragment:fragment-ktx:1.4.1"
}
借助 Fragment KTX 模块，可以使用 lambda 来简化 Fragment 事务，例如：


fragmentManager().commit {
addToBackStack("...")
setCustomAnimations(
R.anim.enter_anim,
R.anim.exit_anim)
add(fragment, "...")
}
还可以使用 viewModels 和 activityViewModels 属性委托在一行中绑定到 ViewModel：


// Get a reference to the ViewModel scoped to this Fragment
val viewModel by viewModels<MyViewModel>()

// Get a reference to the ViewModel scoped to its Activity
val viewModel by activityViewModels<MyViewModel>()

Lifecycle KTX
Lifecycle KTX 为每个 Lifecycle 对象定义一个 LifecycleScope。在此范围内启动的协程会在 Lifecycle 被销毁时取消。您可以使用 lifecycle.coroutineScope 或 lifecycleOwner.lifecycleScope 属性访问 Lifecycle 的 CoroutineScope。

要使用此模块，请将以下内容添加到应用的 build.gradle 文件中：

dependencies {
implementation "androidx.lifecycle:lifecycle-runtime-ktx:2.5.0-alpha06"
}
以下示例演示了如何使用 lifecycleOwner.lifecycleScope 异步创建预计算文本：


class MyFragment: Fragment() {
override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
super.onViewCreated(view, savedInstanceState)
viewLifecycleOwner.lifecycleScope.launch {
val params = TextViewCompat.getTextMetricsParams(textView)
val precomputedText = withContext(Dispatchers.Default) {
PrecomputedTextCompat.create(longTextContent, params)
}
TextViewCompat.setPrecomputedText(textView, precomputedText)
}
}
}

LiveData KTX
使用 LiveData 时，您可能需要异步计算值。例如，您可能需要检索用户的偏好设置并将其传送给界面。在这些情况下，LiveData KTX 可提供一个 liveData 构建器函数，该函数会调用 suspend 函数，并将结果作为 LiveData 对象传送。

要使用此模块，请将以下内容添加到应用的 build.gradle 文件中：

dependencies {
implementation "androidx.lifecycle:lifecycle-livedata-ktx:2.5.0-alpha06"
}
在以下示例中，loadUser() 是在其他地方声明的 suspend 函数。 您可以使用 liveData 构建器函数异步调用 loadUser()，然后使用 emit() 来发出结果：


val user: LiveData<User> = liveData {
val data = database.loadUser() // loadUser is a suspend function.
emit(data)
}
如需详细了解如何将协程与 LiveData 一起使用，请参阅将 Kotlin 协程与架构组件一起使用。

Navigation KTX
Navigation 库的每个组件都有自己的 KTX 版本，用于调整 API 以使其更简洁且更符合 Kotlin 的语言习惯。

要添加这些模块，请将以下内容添加到应用的 build.gradle 文件中：

dependencies {
implementation "androidx.navigation:navigation-runtime-ktx:2.4.2"
implementation "androidx.navigation:navigation-fragment-ktx:2.4.2"
implementation "androidx.navigation:navigation-ui-ktx:2.4.2"
}
您可以使用扩展函数和属性委托来访问目标参数并导航到目标，如以下示例所示：


class MyDestination : Fragment() {

    // Type-safe arguments are accessed from the bundle.
    val args by navArgs<MyDestinationArgs>()

    ...
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<Button>(R.id.next)
            .setOnClickListener {
                // Fragment extension added to retrieve a NavController from
                // any destination.
                findNavController().navigate(R.id.action_to_next_destination)
            }
     }
     ...

}

Palette KTX
Palette KTX 模块为使用调色板提供惯用的 Kotlin 支持。

要使用此模块，请将以下内容添加到应用的 build.gradle 文件中：


dependencies {
implementation "androidx.palette:palette-ktx:1.0.0"
}
例如，使用 Palette 实例时，可以使用 get 运算符 ([ ]) 来检索给定 target 的 selected 色样：


val palette = Palette.from(bitmap).generate()
val swatch = palette[target]


Reactive Streams KTX
利用 Reactive Streams KTX 模块可根据 ReactiveStreams 发布程序来创建可监测的 LiveData 流。

要使用此模块，请将以下内容添加到应用的 build.gradle 文件中：

dependencies {
implementation "androidx.lifecycle:lifecycle-reactivestreams-ktx:2.5.0-alpha06"
}
例如，假设一个数据库只有很少的用户。在您的应用中，您将该数据库加载到内存中，然后在界面中显示用户数据。为此，您可以使用 RxJava。Room Jetpack 组件能以 Flowable 的形式检索用户列表。在这种情况下，您还必须在 Fragment 或 Activity 的整个生命周期内管理 Rx 发布程序订阅。

不过，借助 LiveDataReactiveStreams，您既可以利用 RxJava 及其丰富的运算符和工作安排功能，又可以享受 LiveData 的简便性，如以下示例所示：


val fun getUsersLiveData() : LiveData<List<User>> {
val users: Flowable<List<User>> = dao.findUsers()
return LiveDataReactiveStreams.fromPublisher(users)
}
