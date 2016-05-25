class CreateFarmerTefs < ActiveRecord::Migration
  def change
    create_table :farmer_tefs do |t|
      t.string :text
      t.integer :icon

      t.timestamps null: false
    end
  end
end
