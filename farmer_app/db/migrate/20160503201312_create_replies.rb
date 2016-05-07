class CreateReplies < ActiveRecord::Migration
  def change
    create_table :replies do |t|
      t.string :audio
      t.string :photo
      t.integer :problem_id
      t.integer :id

      t.timestamps null: false
    end
  end
end
