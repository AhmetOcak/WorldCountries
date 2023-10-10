package com.worldcountries.model.country_detail.data.people.age_structure

import com.google.gson.annotations.SerializedName

data class AgeStructure(
    @SerializedName("0_to_14") val zeroToFourTeen: ZeroToFourTeen = ZeroToFourTeen(),
    @SerializedName("15_to_24") val fifteenToTwentyFour: FifteenToTwentyFour = FifteenToTwentyFour(),
    @SerializedName("25_to_54") val twentyFiveToFiftyFour: TwentyFiveToFiftyFour = TwentyFiveToFiftyFour(),
    @SerializedName("55_to_64") val fiftyFiveToSixtyFour: FiftyFiveToSixtyFour = FiftyFiveToSixtyFour(),
    @SerializedName("65_and_over") val sixtyFiveAndOver: SixtyFiveAndOver = SixtyFiveAndOver(),
    @SerializedName("date") val date: String? = null
)