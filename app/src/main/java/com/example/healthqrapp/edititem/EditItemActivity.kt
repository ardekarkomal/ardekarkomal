package com.example.healthqrapp.edititem

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import com.example.healthqrapp.R
import com.example.healthqrapp.base.BaseActivity
import com.example.healthqrapp.base.showMessage
import com.example.healthqrapp.dashboard.DashbordActivity
import com.example.healthqrapp.databinding.ActivityEditItemBinding
import com.example.healthqrapp.itemlist.ItemListActivity
import com.example.healthqrapp.login.LoginActivity
import com.example.healthqrapp.model.ItemListModel
import com.example.healthqrapp.myorder.MyOrderActivity
import com.example.healthqrapp.signup.base.SignUPActivity

class EditItemActivity :BaseActivity() {

    companion object {
        const val FROM = "from"
        const val CATEGORY = "category"
        const val ITEM_TYPE = "item type"
        const val NAME_TEXT = "name"
        const val DESCRIPTION = "description"
        const val UNIT_PRICE = "unit price"
        const val IMAGE = "image"
        const val GALLARY_IMAGE = 101
        const val CAMERA_IMAGE = 102
        const val PERMISSION_CODE = 103
    }

    private lateinit var editItemBinding: ActivityEditItemBinding
    private var filePath=0
    private val category = arrayOf("--Select--", "Life Insurance", "Health Insurance", "Other")
    private val itemType = arrayOf("--Select--", "Individual", "Family Floater", "Group")

    override fun getLayout() = R.layout.activity_edit_item

    override fun init() {
        editItemBinding = DataBindingUtil.setContentView(this, getLayout())
        setSpinnerAdapter()
        editItemBinding.toolbar.tvItem.visibility = View.VISIBLE

        if (intent.getStringExtra(FROM) == "Item List") {
            editItemBinding.tvTitle.text = getString(R.string.edit)
            editItemBinding.ivItemImage.visibility = View.VISIBLE
            editItemBinding.tvNoFile.visibility = View.GONE
            editItemBinding
            setData()
        } else {
            editItemBinding.tvTitle.text = getString(R.string.create)
            editItemBinding.ivItemImage.visibility = View.GONE
        }
    }

    override fun setClickListener() {
        editItemBinding.toolbar.tvSignup.setOnClickListener {
            val i = Intent(this, SignUPActivity::class.java)
            startActivity(i)
        }

        editItemBinding.toolbar.tvLogin.setOnClickListener {
            val i = Intent(this, LoginActivity::class.java)
            startActivity(i)
        }

        editItemBinding.toolbar.tvHome.setOnClickListener {
            val i = Intent(this, DashbordActivity::class.java)
            startActivity(i)
        }

        editItemBinding.toolbar.tvMyAddress.setOnClickListener {
            val i = Intent(this, MyOrderActivity::class.java)
            startActivity(i)
        }

        editItemBinding.cvSave.setOnClickListener {
            if (validation()) {
                showMessage(this,"Create new item successfully!!")
                val i = Intent(this, ItemListActivity::class.java)
                startActivity(i)
            }
        }

        editItemBinding.cvCancel.setOnClickListener {
            onBackPressed()
        }

        editItemBinding.cvChooseImage.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                    val permission = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    requestPermissions(permission, PERMISSION_CODE)
                } else {
                    picImageFromGallary()
                }
            }
        }

    }

    private fun setSpinnerAdapter() {
        val categoryAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, category)
        editItemBinding.spCategory.adapter = categoryAdapter
        val selectedCategory = intent.getStringExtra(CATEGORY).toString()
        val spinnerPosition =categoryAdapter.getPosition(selectedCategory)
        editItemBinding.spCategory.setSelection(spinnerPosition)
        categoryAdapter.notifyDataSetChanged()


        val itemTypeAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, itemType)
        editItemBinding.spItemType.adapter = itemTypeAdapter
        val selectedItemType = intent.getStringExtra(ITEM_TYPE).toString()
        val spinnerItemTypePosition =itemTypeAdapter.getPosition(selectedItemType)
        editItemBinding.spItemType.setSelection(spinnerItemTypePosition)
        itemTypeAdapter.notifyDataSetChanged()
    }

    private fun setData() {
        editItemBinding.etName.setText(intent.getStringExtra(NAME_TEXT))
        editItemBinding.etDescription.setText(intent.getStringExtra(DESCRIPTION))
        editItemBinding.etUnitPrice.setText(intent.getStringExtra(UNIT_PRICE))
        editItemBinding.ivItemImage.setImageResource(intent.getIntExtra(IMAGE, 0))
        val mList = ArrayList<ItemListModel>()
        mList.add(ItemListModel("3",editItemBinding.etName.text.toString(),
            editItemBinding.etDescription.text.toString(),editItemBinding.etUnitPrice.text.toString(),R.drawable.insurance))
    }

    private fun validation(): Boolean {
        val categorySelectedID = editItemBinding.spCategory.selectedItem.toString()
        val itemTypeSelectedID = editItemBinding.spItemType.selectedItem.toString()

        if (categorySelectedID == "--Select--") {
            showMessage(this, "Please selected category.")
            return false
        } else if (itemTypeSelectedID=="--Select--") {
            showMessage(this, "Please selected Item Type.")
            return false
        } else if (editItemBinding.etName.text.toString() == "") {
            showMessage(this, "Please enter name.")
            return false
        } else if (editItemBinding.etDescription.text.toString() == "") {
            showMessage(this, "Please enter description.")
            return false
        } else if (editItemBinding.etUnitPrice.text.toString() == "") {
            showMessage(this, "Please enter unit price.")
            return false
        } else if (editItemBinding.ivItemImage.isGone) {
            editItemBinding.tvNoFile.visibility = View.VISIBLE
            showMessage(this,"Please choose image.")
            return false
        } else {
            return true
        }
    }

    private fun picImageFromGallary(){
        val i = Intent(Intent.ACTION_PICK)
        i.type = "image/*"
        startActivityForResult(i, GALLARY_IMAGE)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode== RESULT_OK && requestCode== GALLARY_IMAGE){
            editItemBinding.ivItemImage.visibility = View.VISIBLE
            editItemBinding.ivItemImage.setImageURI(data?.data)
            editItemBinding.tvNoFile.visibility = View.GONE
        }
    }
}