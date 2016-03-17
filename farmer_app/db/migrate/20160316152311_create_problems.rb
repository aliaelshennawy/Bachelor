class CreateProblems < ActiveRecord::Migration
  def change
    create_table :problems do |t|
      t.string :url
      t.string :text

      t.timestamps null: false
    end
  end
end
