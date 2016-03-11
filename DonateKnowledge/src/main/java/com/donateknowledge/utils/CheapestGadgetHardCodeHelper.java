package com.donateknowledge.utils;

import static com.donateknowledge.utils.CheapestGadgetUtils.getDateToday;
import static com.donateknowledge.utils.CheapestGadgetUtils.stringToBigDecimal;
import static java.util.Arrays.asList;

import java.math.BigDecimal;
import java.math.BigInteger;

import com.donateknowledge.dto.product.Availability;
import com.donateknowledge.dto.product.DimensionUnit;
import com.donateknowledge.dto.product.ProductBody;
import com.donateknowledge.dto.product.ProductCategory;
import com.donateknowledge.dto.product.WeightUnit;
import com.donateknowledge.dto.product.phone.Features;
import com.donateknowledge.dto.product.phone.Memory;
import com.donateknowledge.dto.product.phone.Phone;
import com.donateknowledge.dto.product.phone.PhoneModel;
import com.donateknowledge.dto.product.phone.SimType;
import com.donateknowledge.dto.product.phone.battery.Battery;
import com.donateknowledge.dto.product.phone.battery.BatteryType;
import com.donateknowledge.dto.product.phone.camera.Camera;
import com.donateknowledge.dto.product.phone.camera.CameraFeatures;
import com.donateknowledge.dto.product.phone.camera.CameraFlashType;
import com.donateknowledge.dto.product.phone.camera.CameraType;
import com.donateknowledge.dto.product.phone.connectivity.Connectivity;
import com.donateknowledge.dto.product.phone.connectivity.ConnectivityType;
import com.donateknowledge.dto.product.phone.display.Display;
import com.donateknowledge.dto.product.phone.display.DisplayType;
import com.donateknowledge.dto.product.phone.network.Network;
import com.donateknowledge.dto.product.phone.network.NetworkType;
import com.donateknowledge.dto.product.phone.os.OS;
import com.donateknowledge.dto.product.phone.os.OSPlatform;
import com.donateknowledge.dto.user.User;
import com.donateknowledge.dto.user.UserPhone;
import com.donateknowledge.dto.user.UserRole;

public class CheapestGadgetHardCodeHelper {

	//private static final Logger LOGGER = LoggerFactory.getLogger(CheapestGadgetHardCodeHelper.class);
	public static Phone getSampleCellPhone() {

		Phone phone = new Phone();
		phone.setAvailable(Availability.AVAILABLE.toString());

		ProductBody body = new ProductBody(DimensionUnit.MM.toString(), "138.3", "7.1", "153", WeightUnit.GRAM.toString(), "67.1");
		body.getColors().add("Gold");
		body.getColors().add("Silver");
		body.getColors().add("Grey");
		body.getMaterials().add("Aluminium");
		body.getMaterials().add("Glass");
		phone.setBody(body);

		phone.setListPrice(new BigDecimal("15000"));
		phone.setManufacturer("apple");
		phone.setMrpPrice(new BigDecimal("20000"));

		phone.setPiecesInStock(new BigInteger("10000"));
		phone.setPiecesSold(new BigInteger("1000"));
		phone.setProductCategory(ProductCategory.MOBILE.toString());
		phone.setProductName("iphone6");
		phone.setReleasedDate(getDateToday());



		PhoneModel phoneModel = new PhoneModel();

		Battery battery = new Battery("2750", "80", false, "384", "24", BatteryType.Li_Po.toString(), "11");
		phoneModel.setBattery(battery );

		Camera primary = new Camera("2.2", CameraType.BACK_CAMERA.toString(), CameraFlashType.LED.toString()
				, "29", "12", "1.22", "1/3");
		primary.getFeatures().add(CameraFeatures.AUTO_FOCUS.toString());
		primary.getFeatures().add(CameraFeatures.FACE_DETECTION.toString());
		primary.getFeatures().add(CameraFeatures.SAPPHIRE_CRYSTAL_LENS_COVER.toString());
		primary.getResolution().add("4128 x 3096");
		phoneModel.getCameras().add(primary );

		phoneModel.setChip("Apple A9");

		Connectivity connectivity = new Connectivity();
		connectivity.setBluetoothVersion(ConnectivityType.BLUETOOTH_V4_0.toString());
		connectivity.getGpsType().addAll(asList(ConnectivityType.GPS_A.toString(),
				ConnectivityType.GPS_GLONASS.toString()));
		connectivity.getOtherFeatures().addAll(asList(ConnectivityType.AIR_DROP.toString()
				, ConnectivityType.NFC.toString(), ConnectivityType.OTA_SYNC.toString()));
		connectivity.getUsbType().addAll(asList(ConnectivityType.USB_3_0.toString()));
		connectivity.getWifiType().addAll(asList(ConnectivityType.WIFI_802_11A.toString()));
		phoneModel.setConnectivity(connectivity);

		phoneModel.setCpu("CPU");
		phoneModel.setCpuCores(1);
		phoneModel.setCpuFrequency(stringToBigDecimal("1.4"));

		Display display = new Display("16777216", true, "401", "1080 x 1920", "67.7", "5.5", DisplayType.IPS_LCD.toString());
		display.getFeatures().add(Features.TOUCH_3D.toString());
		display.getFeatures().add(Features.OLEOPHOBIC_COATING.toString());
		display.getFeatures().add(Features.ION_STRENGTHENED_GLASS.toString());
		display.getFeatures().add(Features.SCRATCH_RESISTANT_GLASS.toString());
		phoneModel.setDisplay(display );

		phoneModel.setGpu("GPU");

		Memory memory = new Memory();
		memory.getCardSlotExpendable().add(stringToBigDecimal("64"));
		memory.getInternalStorage().addAll(asList(stringToBigDecimal("16"), stringToBigDecimal("64"), stringToBigDecimal("128")));
		memory.setRam(stringToBigDecimal("2"));
		phoneModel.setMemory(memory);

		phoneModel.setModelName("A1286");

		Network networkConnectivity = new Network();
		networkConnectivity.setEdge(true);
		networkConnectivity.setGprs(true);
		networkConnectivity.getBand2G().add(NetworkType.GSM_850.toString());
		networkConnectivity.getBand2G().add(NetworkType.GSM_900.toString());
		networkConnectivity.getBand2G().add(NetworkType.GSM_1800.toString());
		networkConnectivity.getBand2G().add(NetworkType.GSM_1900.toString());

		networkConnectivity.getBand3G().add(NetworkType.UTMS_850.toString());
		networkConnectivity.getBand3G().add(NetworkType.UTMS_900.toString());
		networkConnectivity.getBand3G().add(NetworkType.UTMS_1700.toString());
		networkConnectivity.getBand3G().add(NetworkType.UTMS_1900.toString());
		networkConnectivity.getBand3G().add(NetworkType.UTMS_2100.toString());

		networkConnectivity.getBand3_5G().add(NetworkType.HSDPA_850.toString());
		networkConnectivity.getBand3_5G().add(NetworkType.HSDPA_900.toString());
		networkConnectivity.getBand3_5G().add(NetworkType.HSDPA_1700.toString());
		networkConnectivity.getBand3_5G().add(NetworkType.HSDPA_1900.toString());
		networkConnectivity.getBand3_5G().add(NetworkType.HSDPA_2100.toString());

		networkConnectivity.getBandLTE().add(NetworkType.LTE_1_2100.toString());
		networkConnectivity.getBandLTE().add(NetworkType.LTE_2_1900.toString());
		networkConnectivity.getBandLTE().add(NetworkType.LTE_3_1800.toString());
		networkConnectivity.getBandLTE().add(NetworkType.LTE_4_1700_2100.toString());
		phoneModel.setNetworkConnectivity(networkConnectivity);

		OS os = new OS();
		os.setOsName(OSPlatform.IOS.toString());
		os.setOsVersion("9.1");
		os.setOsVersionUpgradeableTo("9.2");
		phoneModel.setOs(os);

		phoneModel.getSensors().add(Features.ACCELEROMETER.toString());
		phoneModel.getSensors().add(Features.BAROMETER.toString());
		phoneModel.getSensors().add(Features.COMPASS.toString());
		phoneModel.getSensors().add(Features.FINGERPRINT.toString());
		phoneModel.getSensors().add(Features.GYROSCOPE.toString());

		phoneModel.getSimSlotTypes().add(SimType.MINI.toString());

		phone.getModels().add(phoneModel);
		return phone;
	}

	public static User getSampleUser(UserRole userRole) {
		User user = new User();
		user.setEmail("soni_rahul@live.com");
		UserPhone phone = new UserPhone();
		phone.setPhoneNum(new BigInteger("8696467002"));
		user.getPhone().add(phone);
		user.setFullName("Rahul Soni");
		user.setPassword("password");
		user.getOldPasswords().add("1");
		user.getOldPasswords().add("2");
		user.getOldPasswords().add("3");
		user.getOldPasswords().add("4");
		user.getOldPasswords().add("5");
		user.getOldPasswords().add("6");
		if (userRole == UserRole.ADMIN) {
			user.getUserRole().add(UserRole.ADMIN.toString());
			user.getUserRole().add(UserRole.DBA.toString());
		}
		else if (userRole == UserRole.DBA) {
			user.getUserRole().add(UserRole.DBA.toString());
		}

		return user;

	}
}
