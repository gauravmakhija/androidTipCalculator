package com.yahoo.makhija.androidtipcalculator;

import java.text.DecimalFormat;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	private EditText etBaseAmount;
	private TextView tvTip;
	private TextView tvTotal;
	private Button bt10;
	private Button bt15;
	private Button bt20;
	private static final int COLOR_NOT_SELECTED = -7829368;	//GRAY
	private static final int COLOR_SELECTED = -16776961;	//BLUE
	private static final DecimalFormat decimal2Places = new DecimalFormat("#.##");
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etBaseAmount = (EditText) findViewById(R.id.etBaseAmount);
        tvTip = (TextView) findViewById(R.id.tvTip);
        tvTotal = (TextView) findViewById(R.id.tvTotal);
        bt10 = (Button) findViewById(R.id.bt10);
        bt15 = (Button) findViewById(R.id.bt15);
        bt20 = (Button) findViewById(R.id.bt20);
        setupOnClickListener();
        setupTextChangedListener();
    }
    
    private void setupTextChangedListener() {    	
    	etBaseAmount.addTextChangedListener(new TextWatcher() {
    		public void afterTextChanged(Editable s) {
    		}
    		@Override
    		public void beforeTextChanged(CharSequence s, int start, int count,
    				int after) {
    		}
    		@Override
    		public void onTextChanged(CharSequence s, int start, int before,
    				int count) {
    			if(bt10.isSelected()){
    				handle10PercentTip();
    			}else if(bt15.isSelected()){
    				handle15PercentTip();
    			}else if(bt20.isSelected()){
    				handle20PercentTip();
    			}
    		}
    	});
	}

	private void setupOnClickListener() {
    	bt10.setOnClickListener(new View.OnClickListener() {
    		@Override
    		public void onClick(View v) {
    			handle10PercentTip();
    		}
    	});
    	bt15.setOnClickListener(new View.OnClickListener() {
    		@Override
    		public void onClick(View v) {
    			handle15PercentTip();
    		}
    	});
    	bt20.setOnClickListener(new View.OnClickListener() {
    		@Override
    		public void onClick(View v) {
    			handle20PercentTip();
    		}
    	});
    }
    
	public void handle10PercentTip() {
		Double baseAmount = fetchBaseAmount();
		Double tip = Double.valueOf(decimal2Places.format(baseAmount*0.1));
		Double total = Double.valueOf(decimal2Places.format(baseAmount+baseAmount*0.1));
		tvTip.setText("Tip = $"+tip);
		tvTip.setTextColor(-16776961);
		tvTotal.setText("Total = $"+total);
		selectButton(bt10);
	}
	public void handle15PercentTip() {
		Double baseAmount = fetchBaseAmount();
		Double tip = Double.valueOf(decimal2Places.format(baseAmount*0.15));
		Double total = Double.valueOf(decimal2Places.format(baseAmount+baseAmount*0.15));
		tvTip.setText("Tip = $"+tip);
		tvTip.setTextColor(-16776961);
		tvTotal.setText("Total = $"+total);
		selectButton(bt15);
	}
	public void handle20PercentTip() {
		Double baseAmount = fetchBaseAmount();
		Double tip = Double.valueOf(decimal2Places.format(baseAmount*0.2));
		Double total = Double.valueOf(decimal2Places.format(baseAmount+baseAmount*0.2));
		tvTip.setText("Tip = $"+tip);
		tvTip.setTextColor(-16776961);
		tvTotal.setText("Total = $"+total);
		selectButton(bt20);
	}
    private Double fetchBaseAmount() {
		Double baseAmount;
		try{
			baseAmount = Double.valueOf(etBaseAmount.getText().toString());			
		}catch(NumberFormatException nfe){
			baseAmount = 0.0;
		}
		return baseAmount;
	}
    
    private void selectButton(Button button) {
		if(bt10==button){
			bt10.setBackgroundColor(COLOR_SELECTED);
			bt10.setSelected(true);
			bt15.setBackgroundColor(COLOR_NOT_SELECTED);
			bt15.setSelected(false);
			bt20.setBackgroundColor(COLOR_NOT_SELECTED);
			bt20.setSelected(false);
		}else if(bt15==button){
			bt10.setBackgroundColor(COLOR_NOT_SELECTED);
			bt10.setSelected(false);
			bt15.setBackgroundColor(COLOR_SELECTED);
			bt15.setSelected(true);
			bt20.setBackgroundColor(COLOR_NOT_SELECTED);
			bt20.setSelected(false);
		}else if(bt20==button){
			bt10.setBackgroundColor(COLOR_NOT_SELECTED);
			bt10.setSelected(false);
			bt15.setBackgroundColor(COLOR_NOT_SELECTED);
			bt15.setSelected(false);
			bt20.setBackgroundColor(COLOR_SELECTED);
			bt20.setSelected(true);
		}	
	}
    
}

