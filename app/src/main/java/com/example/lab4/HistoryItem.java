package com.example.lab4;

import android.os.Parcel;
import android.os.Parcelable;

public class HistoryItem implements Parcelable {
    private String sum;
    private String firstNumber;
    private String secondNumber;

    public HistoryItem(String sum, String firstNumber, String secondNumber) {
        this.sum = sum;
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
    }

    public static final Creator<HistoryItem> CREATOR = new Creator<HistoryItem>() {
        @Override
        public HistoryItem createFromParcel(Parcel in) {
            return new HistoryItem(in);
        }

        @Override
        public HistoryItem[] newArray(int size) {
            return new HistoryItem[size];
        }
    };

    public String getTextRepresentation() {
        return String.format("Result of sum %1s and %2s = %3s", firstNumber, secondNumber, sum);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    protected HistoryItem(Parcel in) {
        firstNumber = in.readString();
        secondNumber = in.readString();
        sum = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(firstNumber);
        dest.writeString(secondNumber);
        dest.writeString(sum);
    }
}
