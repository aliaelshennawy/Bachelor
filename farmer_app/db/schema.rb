# encoding: UTF-8
# This file is auto-generated from the current state of the database. Instead
# of editing this file, please use the migrations feature of Active Record to
# incrementally modify your database, and then regenerate this schema definition.
#
# Note that this schema.rb definition is the authoritative source for your
# database schema. If you need to create the application database on another
# system, you should be using db:schema:load, not running all the migrations
# from scratch. The latter is a flawed and unsustainable approach (the more migrations
# you'll amass, the slower it'll run and the greater likelihood for issues).
#
# It's strongly recommended that you check this file into your version control system.

ActiveRecord::Schema.define(version: 20160525153532) do

  create_table "advices", force: :cascade do |t|
    t.datetime "created_at",             null: false
    t.datetime "updated_at",             null: false
    t.string   "audio",      limit: 255
    t.integer  "user_id",    limit: 4
    t.string   "photo",      limit: 255
  end

  create_table "crops", force: :cascade do |t|
    t.string   "name",       limit: 255
    t.string   "img",        limit: 255
    t.datetime "created_at",             null: false
    t.datetime "updated_at",             null: false
  end

  create_table "engineerlists", force: :cascade do |t|
    t.string   "title",      limit: 255
    t.integer  "icon",       limit: 4
    t.datetime "created_at",             null: false
    t.datetime "updated_at",             null: false
  end

  create_table "engineers", force: :cascade do |t|
    t.string   "speciality", limit: 255
    t.datetime "created_at",             null: false
    t.datetime "updated_at",             null: false
  end

  create_table "farmer_lists", force: :cascade do |t|
    t.string   "title",      limit: 255
    t.integer  "icon",       limit: 4
    t.datetime "created_at",             null: false
    t.datetime "updated_at",             null: false
  end

  create_table "farmer_tefs", force: :cascade do |t|
    t.string   "text",       limit: 255
    t.integer  "icon",       limit: 4
    t.datetime "created_at",             null: false
    t.datetime "updated_at",             null: false
  end

  create_table "farmerlists", force: :cascade do |t|
    t.text     "title",      limit: 65535
    t.integer  "icon",       limit: 4
    t.datetime "created_at",               null: false
    t.datetime "updated_at",               null: false
  end

  create_table "farmers", force: :cascade do |t|
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
  end

  create_table "msg_threads", force: :cascade do |t|
    t.boolean  "solved"
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
  end

  create_table "notifications", force: :cascade do |t|
    t.string   "text",       limit: 255
    t.datetime "created_at",             null: false
    t.datetime "updated_at",             null: false
    t.integer  "user_id",    limit: 4
    t.integer  "cause_id",   limit: 4
    t.string   "title",      limit: 255
  end

  create_table "pictures", force: :cascade do |t|
    t.string   "string",     limit: 255
    t.string   "name",       limit: 255
    t.datetime "created_at",             null: false
    t.datetime "updated_at",             null: false
  end

  create_table "problems", force: :cascade do |t|
    t.string   "title",              limit: 255
    t.datetime "created_at",                     null: false
    t.datetime "updated_at",                     null: false
    t.string   "image_file_name",    limit: 255
    t.string   "image_content_type", limit: 255
    t.integer  "image_file_size",    limit: 4
    t.datetime "image_updated_at"
    t.string   "photo",              limit: 255
    t.string   "audio",              limit: 255
    t.integer  "user_id",            limit: 4
  end

  create_table "replies", force: :cascade do |t|
    t.string   "audio",      limit: 255
    t.string   "photo",      limit: 255
    t.integer  "problem_id", limit: 4
    t.integer  "user_id",    limit: 4
    t.datetime "created_at",             null: false
    t.datetime "updated_at",             null: false
  end

  create_table "user_problems", force: :cascade do |t|
    t.integer  "user_a_id",  limit: 4
    t.integer  "user_b_id",  limit: 4
    t.integer  "problem_id", limit: 4
    t.datetime "created_at",           null: false
    t.datetime "updated_at",           null: false
  end

  create_table "users", force: :cascade do |t|
    t.string   "name",             limit: 255
    t.string   "password_digest",  limit: 255
    t.datetime "created_at",                   null: false
    t.datetime "updated_at",                   null: false
    t.string   "status",           limit: 255
    t.integer  "user_id",          limit: 4
    t.string   "registeration_id", limit: 255
  end

end
