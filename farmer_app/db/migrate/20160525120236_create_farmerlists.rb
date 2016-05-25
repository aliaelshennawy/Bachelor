class CreateFarmerlists < ActiveRecord::Migration
  def change
    create_table :farmerlists do |t|
      t.text :title
      t.integer :icon

      t.timestamps null: false
    end
  end
end
