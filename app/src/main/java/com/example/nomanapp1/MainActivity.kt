package com.example.nomanapp1

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // Mood button variables
    private lateinit var happyBtn: ImageButton
    private lateinit var sadBtn: ImageButton
    private lateinit var angryBtn: ImageButton
    private lateinit var tiredBtn: ImageButton
    private lateinit var anxiousBtn: ImageButton
    private lateinit var boredBtn: ImageButton
    private lateinit var excitedBtn: ImageButton
    private lateinit var infoBtn: ImageButton  // info button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Connect layout buttons to Kotlin code
        happyBtn = findViewById(R.id.happyBtn)
        sadBtn = findViewById(R.id.sadBtn)
        angryBtn = findViewById(R.id.angryBtn)
        tiredBtn = findViewById(R.id.tiredBtn)
        anxiousBtn = findViewById(R.id.anxiousBtn)
        boredBtn = findViewById(R.id.boredBtn)
        excitedBtn = findViewById(R.id.excitedBtn)
        infoBtn = findViewById(R.id.infoBtn)

        setupMoodButtons()

        infoBtn.setOnClickListener {
            showInstructionsModal()
        }
    }

    private fun setupMoodButtons() {
        val moodMap = mapOf(
            happyBtn to "happy",
            sadBtn to "sad",
            angryBtn to "angry",
            tiredBtn to "tired",
            anxiousBtn to "anxious",
            boredBtn to "bored",
            excitedBtn to "excited"
        )

        moodMap.forEach { (button, mood) ->
            button.setOnClickListener {
                val result = getFoodSuggestion(mood)
                showFoodModal(result)
            }
        }
    }

    private fun getFoodSuggestion(mood: String): FoodSuggestion {
        return when (mood) {
            "happy" -> FoodSuggestion("Ice Cream", R.drawable.ice_cream, "Sweet and cold, perfect for happy vibes!")
            "sad" -> FoodSuggestion("Mac and Cheese", R.drawable.mac_cheese, "Warm, cheesy comfort food to lift your spirits.")
            "angry" -> FoodSuggestion("Spicy Ramen", R.drawable.spicy_ramen, "Let the heat burn off that frustration!")
            "tired" -> FoodSuggestion("Pancakes", R.drawable.pancakes, "Soft and fluffy pancakes to recharge you.")
            "anxious" -> FoodSuggestion("Green Tea", R.drawable.green_tea, "A calming drink to soothe your nerves.")
            "bored" -> FoodSuggestion("Popcorn", R.drawable.popcorn, "Fun to eat while watching something new!")
            "excited" -> FoodSuggestion("Pizza", R.drawable.pizza, "Party food that matches your energy!")
            else -> FoodSuggestion("Snack", R.drawable.snack, "Default comfort food.")
        }
    }

    private fun showFoodModal(suggestion: FoodSuggestion) {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.modal_food_result, null)

        val foodImage: ImageView = dialogView.findViewById(R.id.foodImage)
        val foodName: TextView = dialogView.findViewById(R.id.foodName)
        val foodDescription: TextView = dialogView.findViewById(R.id.foodDescription)

        foodImage.setImageResource(suggestion.imageRes)
        foodName.text = suggestion.name
        foodDescription.text = suggestion.description

        AlertDialog.Builder(this)
            .setView(dialogView)
            .setCancelable(true)
            .show()
    }

    private fun showInstructionsModal() {
        val scrollView = ScrollView(this).apply {
            setPadding(48, 48, 48, 48)  // padding in pixels, adjust if needed
            setBackgroundColor(android.graphics.Color.WHITE)
        }

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER_HORIZONTAL
        }

        val title = TextView(this).apply {
            text = "How to Use Food Mood Calculator"
            textSize = 22f
            setTypeface(null, android.graphics.Typeface.BOLD)
            setPadding(0, 0, 0, 32)
        }

        val instructions = TextView(this).apply {
            text = "1. Select how you are feeling today by tapping one of the mood icons.\n\n" +
                    "2. A delicious food suggestion will pop up to match your mood.\n\n" +
                    "3. Enjoy your food and feel better!\n\n" +
                    "Have fun and explore different moods!"
            textSize = 16f
            setLineSpacing(1.2f, 1.2f)
            gravity = Gravity.CENTER
        }

        layout.addView(title)
        layout.addView(instructions)
        scrollView.addView(layout)

        AlertDialog.Builder(this)
            .setView(scrollView)
            .setCancelable(true)
            .show()
    }

    data class FoodSuggestion(val name: String, val imageRes: Int, val description: String)
}
