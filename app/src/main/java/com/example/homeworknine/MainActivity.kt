package com.example.homeworknine

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homeworknine.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val recyclerItems = mutableListOf<Item>()
    private val filterItems = listOf(CategoryType.ALL, CategoryType.PARTY,
        CategoryType.CAMPING, CategoryType.BUSINESS, CategoryType.SPORTS, CategoryType.WINTER)

    private lateinit var recyclerAdapter: ItemRecyclerAdapter
    private lateinit var filterRecyclerAdapter: FilterItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUp()
    }

    private fun setUp() {
        fillRecyclerList()
        initializeRecycler()
        initializeFilterRecycler()
        recyclerAdapter.setOriginalList(recyclerItems)
    }

    private fun initializeRecycler() {
        recyclerAdapter = ItemRecyclerAdapter()
        with(binding.recyclerView) {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            adapter = recyclerAdapter
            addItemDecoration(ItemDecoration(2, 30))
        }
    }

    private fun initializeFilterRecycler() {
        filterRecyclerAdapter = FilterItemAdapter()
        with(binding.rvFilterItem) {
            layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.HORIZONTAL, false)
            adapter = filterRecyclerAdapter
        }

        filterRecyclerAdapter.setData(filterItems)
        filterRecycler()
    }

    private fun filterRecycler() {
        filterRecyclerAdapter.onFilterClick = { category: CategoryType, button: AppCompatButton ->
            binding.recyclerView.layoutManager?.smoothScrollToPosition(binding.recyclerView, null, 0)
            changeButtonDesign(button)
            filterList(category)
        }
    }

    private fun filterList(category: CategoryType) {
        if (category == CategoryType.ALL) {
            recyclerAdapter.submitList(recyclerItems)
        } else {
            val filteredList = recyclerItems.filter { item -> item.categoryType == category }
            recyclerAdapter.submitList(filteredList)
        }
    }

    private fun changeButtonDesign(button: AppCompatButton) {
        button.setBackgroundResource(R.drawable.background_selected_button)
        button.setTextColor(Color.WHITE)
    }


    private fun fillRecyclerList() {
        recyclerItems.add(
            Item(
                generateRandomNumber(),
                R.drawable.cloth1,
                "Blabla",
                69,
                CategoryType.BUSINESS
            )
        )
        recyclerItems.add(
            Item(
                generateRandomNumber(),
                R.drawable.cloth2,
                "Blabla1",
                99,
                CategoryType.BUSINESS
            )
        )
        recyclerItems.add(
            Item(
                generateRandomNumber(),
                R.drawable.cloth4,
                "Blabla2",
                9,
                CategoryType.CAMPING
            )
        )
        recyclerItems.add(
            Item(
                generateRandomNumber(),
                R.drawable.cloth3,
                "Blabla3",
                10,
                CategoryType.WINTER
            )
        )
        recyclerItems.add(
            Item(
                generateRandomNumber(),
                R.drawable.cloth3,
                "Blabla4",
                25,
                CategoryType.PARTY
            )
        )
        recyclerItems.add(
            Item(
                generateRandomNumber(),
                R.drawable.cloth2,
                "Blabla5",
                60,
                CategoryType.CAMPING
            )
        )
        recyclerItems.add(
            Item(
                generateRandomNumber(),
                R.drawable.cloth1,
                "Blabla6",
                999,
                CategoryType.BUSINESS
            )
        )
        recyclerItems.add(
            Item(
                generateRandomNumber(),
                R.drawable.cloth1,
                "Blabla6",
                999,
                CategoryType.SPORTS
            )
        )
        recyclerItems.add(
            Item(
                generateRandomNumber(),
                R.drawable.cloth3,
                "Blabla6",
                999,
                CategoryType.SPORTS
            )
        )
        recyclerItems.add(
            Item(
                generateRandomNumber(),
                R.drawable.cloth4,
                "Blabla6",
                999,
                CategoryType.CAMPING
            )
        )
        recyclerItems.add(
            Item(
                generateRandomNumber(),
                R.drawable.cloth4,
                "Blabla6",
                999,
                CategoryType.WINTER
            )
        )
        recyclerItems.add(
            Item(
                generateRandomNumber(),
                R.drawable.cloth1,
                "Blabla6",
                999,
                CategoryType.PARTY
            )
        )
        recyclerItems.add(
            Item(
                generateRandomNumber(),
                R.drawable.cloth2,
                "Blabla6",
                999,
                CategoryType.PARTY
            )
        )
        recyclerItems.add(
            Item(
                generateRandomNumber(),
                R.drawable.cloth1,
                "Blabla6",
                999,
                CategoryType.SPORTS
            )
        )
        recyclerItems.add(
            Item(
                generateRandomNumber(),
                R.drawable.cloth2,
                "Blabla6",
                999,
                CategoryType.WINTER
            )
        )
    }

    private fun generateRandomNumber(): Int {
        val random = Random(1000)
        return random.nextInt()
    }
}