class CreateFarmerLists < ActiveRecord::Migration
  def change
    create_table :farmer_lists do |t|
      t.string :title
      t.integer :icon

      t.timestamps null: false
    end
  end
end
