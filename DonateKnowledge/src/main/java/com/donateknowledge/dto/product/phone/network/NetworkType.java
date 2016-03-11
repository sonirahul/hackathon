package com.donateknowledge.dto.product.phone.network;

import com.donateknowledge.dto.product.EnumInterface;

public enum NetworkType implements EnumInterface {

	GSM_850("GSM 850"),
	GSM_900("GSM 900"),
	GSM_1800("GSM 1800"),
	GSM_1900("GSM 1900"),
	CDMA_800("CDMA 800"),
	CDMA_1700("CDMA 1700"),
	CDMA_1900("CDMA 1900"),
	CDMA_2100("CDMA 2100"),
	UTMS_850("UTMS 850"),
	UTMS_900("UTMS 900"),
	UTMS_1700("UTMS 1700"),
	UTMS_1900("UTMS 1900"),
	UTMS_2100("UTMS 2100"),
	HSDPA_850("HSDPA 850"),
	HSDPA_900("HSDPA 900"),
	HSDPA_1700("HSDPA 1700"),
	HSDPA_1900("HSDPA 1900"),
	HSDPA_2100("HSDPA 2100"),
	LTE_1_2100("LTE 1(2100)"),
	LTE_2_1900("LTE 2(1900)"),
	LTE_3_1800("LTE 3(1800)"),
	LTE_4_1700_2100("LTE 4(1700/2100)"),
	LTE_5_850("LTE 5(850)"),
	LTE_6_900("LTE 6(900)"),
	LTE_7_2600("LTE 7(2600)"),
	LTE_8_900("LTE 8(900)"),
	LTE_9_1800("LTE 9(1800)"),
	LTE_10_1700_2100("LTE 10(1700/2100)"),
	LTE_11_1500("LTE 11(1500)"),
	LTE_12_700("LTE 12(700)"),
	LTE_13_700("LTE 13(700)"),
	LTE_14_700("LTE 14(700)"),
	LTE_17_700("LTE 17(700)"),
	LTE_18_800("LTE 18(800)"),
	LTE_19_800("LTE 19(800)"),
	LTE_20_800("LTE 20(800)"),
	LTE_21_1500("LTE 21(1500)"),
	LTE_22_3500("LTE 22(3500)"),
	LTE_23_2000("LTE 23(2000)"),
	LTE_24_1600("LTE 24(1600)"),
	LTE_25_1900("LTE 25(1900)"),
	LTE_26_850("LTE 26(850)"),
	LTE_27_800("LTE 27(800)"),
	LTE_28_700("LTE 28(700)"),
	LTE_29_700("LTE 29(700)"),
	LTE_30_2300("LTE 30(2300)"),
	LTE_31_450("LTE 31(450)"),
	LTE_32_1500("LTE 32(1500)"),
	LTE_33_1900("LTE 33(1900)"),
	LTE_34_200("LTE 34(2000)"),
	LTE_35_L_GAP("LTE 35(L Gap)"),
	LTE_36_U_GAP("LTE 36(U Gap)"),
	LTE_37_C_GAP("LTE 37(C Gap)"),
	LTE_38_2600("LTE 38(2600)"),
	LTE_39_1900("LTE 39(1900)"),
	LTE_40_2300("LTE 40(2300)"),
	LTE_41_2500("LTE 41(2500)"),
	LTE_42_3500("LTE 42(3500)"),
	LTE_43_3700("LTE 43(3700)"),
	LTE_44_700("LTE 44(700)");

	private final String value;

	private NetworkType(String value) {
		this.value = value;
	}

	public static NetworkType getNetworkType(String string) {
		for (NetworkType networkType : NetworkType.values()) {
			if (networkType.getValue().equals(string)) {
				return networkType;
			}
		}
		return null;
	}

	public String getValue() {
		return this.value;
	}

	@Override
	public String toString(){
		return this.getValue();
	}
}
