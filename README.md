


#### 当前版本 : currentVersion

[![](https://jitpack.io/v/yzhg0854/DialogFragment.svg)](https://jitpack.io/#yzhg0854/DialogFragment)

#### 在项目build.gradle中插入
```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

####添加依赖

```
dependencies {
	implementation 'com.github.yzhg-ux:DialogFragment:currentVersion'
}
```

#### 简单使用

```
CommonDialog commonDialog = CommonDialog.newInstance().setLayoutId(R.layout.common_layout);
commonDialog.setConvertListener((holder, dialog) -> holder.getView(R.id.butSureDialog).setOnClickListener(v1 -> {
        commonDialog.dismissDialog();
}));
commonDialog.showDialog(getSupportFragmentManager());
```
