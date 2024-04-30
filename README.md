# MoviesAPI
Moviepedia demonstrates modern Android development built with the [Modern Android App Architecture](https://developer.android.com/courses/pathways/android-architecture).<br>

Several supporting libraries in making Movipedia :
1. Kotlin Coroutines
2. Kotlin Flow
3. Room Database
4. Dagger 2
5. Epoxy
6. Kover

# About
This app demonstrates how to implement Epoxy for building complex screens in a RecyclerView.
# Epoxy
Example of ViewBindingEpoxyModelWithHolder to support ViewBinding

```
abstract class ViewBindingEpoxyModelWithHolder<in T : ViewBinding> :
    EpoxyModelWithHolder<ViewBindingHolder>() {

    @Suppress("UNCHECKED_CAST")
    override fun bind(holder: ViewBindingHolder) {
        (holder.viewBinding as T).bind()
    }

    abstract fun T.bind()

    @Suppress("UNCHECKED_CAST")
    override fun unbind(holder: ViewBindingHolder) {
        (holder.viewBinding as T).unbind()
    }

    open fun T.unbind() {}

    override fun createNewHolder(parent: ViewParent): ViewBindingHolder {
        return ViewBindingHolder(this::class.java)
    }
}

// Static cache of a method pointer for each type of item used.
private val sBindingMethodByClass = ConcurrentHashMap<Class<*>, Method>()

@Suppress("UNCHECKED_CAST")
@Synchronized
private fun getBindMethodFrom(javaClass: Class<*>): Method =
    sBindingMethodByClass.getOrPut(javaClass) {
        val actualTypeOfThis = getSuperclassParameterizedType(javaClass)
        val viewBindingClass = actualTypeOfThis.actualTypeArguments[0] as Class<ViewBinding>
        viewBindingClass.getDeclaredMethod("bind", View::class.java)
            ?: error("The binder class ${javaClass.canonicalName} should have a method bind(View)")
    }

private fun getSuperclassParameterizedType(klass: Class<*>): ParameterizedType {
    val genericSuperclass = klass.genericSuperclass
    return (genericSuperclass as? ParameterizedType)
        ?: getSuperclassParameterizedType(genericSuperclass as Class<*>)
}

class ViewBindingHolder(private val epoxyModelClass: Class<*>) : EpoxyHolder() {
    // Using reflection to get the static binding method.
    // Lazy so it's computed only once by instance, when the 1st ViewHolder is actually created.
    private val bindingMethod by lazy { getBindMethodFrom(epoxyModelClass) }

    internal lateinit var viewBinding: ViewBinding
    override fun bindView(itemView: View) {
        // The 1st param is null because the binding method is static.
        viewBinding = bindingMethod.invoke(null, itemView) as ViewBinding
    }
}
```

Example of how to use ViewBindingEpoxyModelWithHolder
```
class EpoxyErrorSearchMovie :
    ViewBindingEpoxyModelWithHolder<ErrorItemSearchMovieBinding>() {
    override fun ErrorItemSearchMovieBinding.bind() {
        ivError.load(AppCompatResources.getDrawable(root.context, R.drawable.empty_image))
    }

    override fun getDefaultLayout(): Int {
        return R.layout.error_item_search_movie
    }
}
```

Example of VerticalGridCarousel, this class created to support GridLayout. just build your project and Epoxy will generated this EpoxyModel for you.
Things to consider to use this class are: spanCount of GridLayoutManager & Epoxy autoLayout configuration
```
@ModelView(autoLayout = ModelView.Size.WRAP_WIDTH_WRAP_HEIGHT, fullSpan = true)
class VerticalGridCarousel(context: Context) : Carousel(context) {
    override fun createLayoutManager(): LayoutManager {
        return GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
    }
}
```


Example of how use VerticalGridCarousel
```
VerticalGridCarouselModel_()
                .id("your_id")
                .models(carouselTopRatedModel)
                .numViewsToShowOnScreen(2f)
                .addTo(this)
```

# Dependecy Injection : Dagger 2
Even though Hilt as a DI is easier to use than Dagger 2, this application still uses Dagger 2 because of my desire to learn the basics of Dagger, such as how to create a DaggerModule, DaggerComponent, and DaggerScope

