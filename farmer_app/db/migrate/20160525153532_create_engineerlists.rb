class CreateEngineerlists < ActiveRecord::Migration
  def change
    create_table :engineerlists do |t|
      t.string :title
      t.integer :icon

      t.timestamps null: false
    end
  end
end
