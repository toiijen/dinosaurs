
public static class Modelpterodaktyl extends ModelBase {
	private final ModelRenderer root;
	private final ModelRenderer body;
	private final ModelRenderer neck_rear;
	private final ModelRenderer neck_mid;
	private final ModelRenderer neck_end;
	private final ModelRenderer head;
	private final ModelRenderer head_r1;
	private final ModelRenderer head_r2;
	private final ModelRenderer head_r3;
	private final ModelRenderer head_r4;
	private final ModelRenderer head_r5;
	private final ModelRenderer jaw;
	private final ModelRenderer right_wing_rear;
	private final ModelRenderer right_hand;
	private final ModelRenderer right_ring;
	private final ModelRenderer right_index;
	private final ModelRenderer right_middle;
	private final ModelRenderer right_wing_elbow;
	private final ModelRenderer right_wing_elbow_r1;
	private final ModelRenderer right_wing_elbow_r2;
	private final ModelRenderer left_wing_rear;
	private final ModelRenderer left_hand;
	private final ModelRenderer left_index;
	private final ModelRenderer left_middle;
	private final ModelRenderer left_ring;
	private final ModelRenderer left_wing_elbow;
	private final ModelRenderer left_wing_elbow_r1;
	private final ModelRenderer left_wing_elbow_r2;
	private final ModelRenderer midSection;
	private final ModelRenderer pelvis;
	private final ModelRenderer left_leg_rear;
	private final ModelRenderer left_leg_front;
	private final ModelRenderer left_foot;
	private final ModelRenderer left_claws;
	private final ModelRenderer right_leg_rear;
	private final ModelRenderer right_leg_front;
	private final ModelRenderer right_foot;
	private final ModelRenderer right_claws;
	private final ModelRenderer tail;

	public Modelpterodaktyl() {
		textureWidth = 16;
		textureHeight = 16;

		root = new ModelRenderer(this);
		root.setRotationPoint(0.0F, 20.75F, 1.5F);

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 0.0F, 0.0F);
		root.addChild(body);
		body.cubeList.add(new ModelBox(body, 8, 11, -0.8F, -0.75F, -2.5F, 1, 1, 2, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 10, 12, -0.7F, -0.65F, -2.7F, 1, 0, 0, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 10, 12, -0.4F, -0.55F, -2.9F, 0, 0, 0, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 0, 12, -0.6F, 0.35F, -2.4F, 1, 0, 2, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 0, 12, 0.6F, 0.35F, -2.3F, 0, 0, 2, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 0, 12, -0.7F, 0.35F, -2.3F, 0, 0, 2, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 10, 12, -0.9F, -0.65F, -2.4F, 0, 0, 0, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 10, 12, 0.8F, -0.65F, -2.4F, 0, 0, 0, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 10, 12, -0.9F, -0.55F, -1.5F, 0, 0, 0, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 10, 12, 0.8F, -0.55F, -1.5F, 0, 0, 0, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 10, 12, -0.9F, -0.45F, -0.9F, 0, 0, 0, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 10, 12, 0.8F, -0.45F, -0.9F, 0, 0, 0, 0.0F, false));

		neck_rear = new ModelRenderer(this);
		neck_rear.setRotationPoint(0.0F, -0.35F, -2.9F);
		body.addChild(neck_rear);
		neck_rear.cubeList.add(new ModelBox(neck_rear, 8, 12, -0.4F, -0.2F, -0.6F, 0, 0, 0, 0.0F, false));
		neck_rear.cubeList.add(new ModelBox(neck_rear, 4, 13, -0.3F, 0.4F, -0.6F, 0, 0, 0, 0.0F, false));

		neck_mid = new ModelRenderer(this);
		neck_mid.setRotationPoint(0.0F, -0.125F, -0.7F);
		neck_rear.addChild(neck_mid);
		neck_mid.cubeList.add(new ModelBox(neck_mid, 9, 13, -0.4F, -0.075F, -0.5F, 0, 0, 0, 0.0F, false));
		neck_mid.cubeList.add(new ModelBox(neck_mid, 4, 13, -0.3F, 0.525F, -0.5F, 0, 0, 0, 0.0F, false));

		neck_end = new ModelRenderer(this);
		neck_end.setRotationPoint(0.0F, 0.0F, -0.6F);
		neck_mid.addChild(neck_end);
		neck_end.cubeList.add(new ModelBox(neck_end, 8, 14, -0.4F, -0.075F, -0.5F, 0, 0, 0, 0.0F, false));
		neck_end.cubeList.add(new ModelBox(neck_end, 4, 13, -0.3F, 0.525F, -0.5F, 0, 0, 0, 0.0F, false));

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 0.3952F, -0.429F);
		neck_end.addChild(head);
		head.cubeList.add(new ModelBox(head, 10, 13, -0.3F, -0.2702F, -2.271F, 0, 0, 1, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 10, 13, -0.2F, -0.2702F, -3.571F, 0, 0, 1, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 10, 13, -0.2F, -0.3702F, -2.171F, 0, 0, 1, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 13, 4, -0.4F, -0.5702F, -0.871F, 0, 0, 0, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 13, 4, -0.4F, -0.5702F, -0.871F, 0, 0, 0, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 2, 12, -0.3F, 0.0298F, -0.871F, 0, 0, 0, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 8, 7, 0.41F, -0.3702F, -0.771F, 0, 0, 0, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 8, 7, -0.41F, -0.3702F, -0.771F, 0, 0, 0, 0.0F, true));
		head.cubeList.add(new ModelBox(head, 8, 7, 0.41F, -0.4702F, -0.521F, 0, 0, 0, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 8, 7, -0.41F, -0.4702F, -0.521F, 0, 0, 0, 0.0F, true));
		head.cubeList.add(new ModelBox(head, 8, 7, 0.409F, -0.2702F, -0.521F, 0, 0, 0, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 8, 7, -0.409F, -0.2702F, -0.521F, 0, 0, 0, 0.0F, true));
		head.cubeList.add(new ModelBox(head, 8, 7, 0.409F, -0.3702F, -0.271F, 0, 0, 0, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 8, 7, -0.409F, -0.3702F, -0.271F, 0, 0, 0, 0.0F, true));
		head.cubeList.add(new ModelBox(head, 14, 7, 0.41F, -0.3702F, -0.521F, 0, 0, 0, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 14, 7, -0.41F, -0.3702F, -0.521F, 0, 0, 0, 0.0F, true));

		head_r1 = new ModelRenderer(this);
		head_r1.setRotationPoint(0.0F, -0.5046F, -1.1886F);
		head.addChild(head_r1);
		setRotationAngle(head_r1, 0.2618F, 0.0F, 0.0F);
		head_r1.cubeList.add(new ModelBox(head_r1, 10, 13, -0.1F, -0.1F, -0.2F, 0, 0, 1, 0.0F, false));

		head_r2 = new ModelRenderer(this);
		head_r2.setRotationPoint(0.0F, -0.7634F, -0.2227F);
		head.addChild(head_r2);
		setRotationAngle(head_r2, 0.2618F, 0.0F, 0.0F);
		head_r2.cubeList.add(new ModelBox(head_r2, 10, 13, -0.1F, -0.1F, -0.2F, 0, 0, 1, 0.0F, false));

		head_r3 = new ModelRenderer(this);
		head_r3.setRotationPoint(0.0F, -0.5702F, -0.171F);
		head.addChild(head_r3);
		setRotationAngle(head_r3, 0.2618F, 0.0F, 0.0F);
		head_r3.cubeList.add(new ModelBox(head_r3, 10, 13, -0.1F, -0.1F, -0.4F, 0, 0, 0, 0.0F, false));

		head_r4 = new ModelRenderer(this);
		head_r4.setRotationPoint(0.0F, -0.3202F, -2.821F);
		head.addChild(head_r4);
		setRotationAngle(head_r4, 0.0436F, 0.0F, 0.0F);
		head_r4.cubeList.add(new ModelBox(head_r4, 10, 13, -0.1F, 0.0F, -0.65F, 0, 0, 1, 0.0F, false));

		head_r5 = new ModelRenderer(this);
		head_r5.setRotationPoint(0.0F, -0.4202F, -1.421F);
		head.addChild(head_r5);
		setRotationAngle(head_r5, 0.0873F, 0.0F, 0.0F);
		head_r5.cubeList.add(new ModelBox(head_r5, 10, 13, -0.1F, -0.05F, -0.65F, 0, 0, 1, 0.0F, false));

		jaw = new ModelRenderer(this);
		jaw.setRotationPoint(0.0F, 0.0131F, -0.8376F);
		head.addChild(jaw);
		jaw.cubeList.add(new ModelBox(jaw, 2, 12, -0.2F, -0.0833F, -1.3333F, 0, 0, 1, 0.0F, false));
		jaw.cubeList.add(new ModelBox(jaw, 2, 12, -0.1F, 0.0167F, -1.2333F, 0, 0, 1, 0.0F, false));
		jaw.cubeList.add(new ModelBox(jaw, 2, 12, -0.1F, -0.0833F, -2.6333F, 0, 0, 1, 0.0F, false));

		right_wing_rear = new ModelRenderer(this);
		right_wing_rear.setRotationPoint(-0.95F, -0.2F, -1.95F);
		body.addChild(right_wing_rear);
		right_wing_rear.cubeList.add(new ModelBox(right_wing_rear, 7, 12, -3.45F, -0.15F, -0.35F, 3, 0, 0, 0.0F, true));
		right_wing_rear.cubeList.add(new ModelBox(right_wing_rear, 10, 13, -3.55F, -0.15F, -0.4F, 0, 0, 0, 0.0F, true));
		right_wing_rear.cubeList.add(new ModelBox(right_wing_rear, 0, 2, -3.45F, -0.05F, -0.1F, 3, 0, 2, 0.0F, true));

		right_hand = new ModelRenderer(this);
		right_hand.setRotationPoint(-3.4F, 0.0F, -0.45F);
		right_wing_rear.addChild(right_hand);

		right_ring = new ModelRenderer(this);
		right_ring.setRotationPoint(-0.1F, 0.0F, 0.025F);
		right_hand.addChild(right_ring);
		right_ring.cubeList.add(new ModelBox(right_ring, 11, 6, -0.05F, -0.05F, -0.225F, 0, 0, 0, 0.0F, false));

		right_index = new ModelRenderer(this);
		right_index.setRotationPoint(0.2F, 0.0F, 0.025F);
		right_hand.addChild(right_index);
		right_index.cubeList.add(new ModelBox(right_index, 11, 6, -0.05F, -0.05F, -0.225F, 0, 0, 0, 0.0F, false));

		right_middle = new ModelRenderer(this);
		right_middle.setRotationPoint(0.05F, 0.0F, 0.025F);
		right_hand.addChild(right_middle);
		right_middle.cubeList.add(new ModelBox(right_middle, 11, 6, -0.05F, -0.05F, -0.225F, 0, 0, 0, 0.0F, false));

		right_wing_elbow = new ModelRenderer(this);
		right_wing_elbow.setRotationPoint(-3.55F, -0.05F, -0.225F);
		right_wing_rear.addChild(right_wing_elbow);

		right_wing_elbow_r1 = new ModelRenderer(this);
		right_wing_elbow_r1.setRotationPoint(0.0934F, 0.05F, 0.2712F);
		right_wing_elbow.addChild(right_wing_elbow_r1);
		setRotationAngle(right_wing_elbow_r1, 0.0F, 0.1745F, 0.0F);
		right_wing_elbow_r1.cubeList
				.add(new ModelBox(right_wing_elbow_r1, 0, 0, -3.15F, -0.05F, -0.15F, 3, 0, 2, 0.0F, false));

		right_wing_elbow_r2 = new ModelRenderer(this);
		right_wing_elbow_r2.setRotationPoint(0.05F, 0.15F, 0.025F);
		right_wing_elbow.addChild(right_wing_elbow_r2);
		setRotationAngle(right_wing_elbow_r2, 0.0F, 0.1745F, 0.0F);
		right_wing_elbow_r2.cubeList
				.add(new ModelBox(right_wing_elbow_r2, 7, 12, -3.15F, -0.25F, -0.15F, 3, 0, 0, 0.0F, true));

		left_wing_rear = new ModelRenderer(this);
		left_wing_rear.setRotationPoint(0.95F, -0.2F, -1.95F);
		body.addChild(left_wing_rear);
		left_wing_rear.cubeList.add(new ModelBox(left_wing_rear, 8, 12, -0.05F, -0.15F, -0.35F, 3, 0, 0, 0.0F, false));
		left_wing_rear.cubeList.add(new ModelBox(left_wing_rear, 11, 12, 3.15F, -0.15F, -0.4F, 0, 0, 0, 0.0F, false));
		left_wing_rear.cubeList.add(new ModelBox(left_wing_rear, 0, 2, -0.05F, -0.05F, -0.1F, 3, 0, 2, 0.0F, true));

		left_hand = new ModelRenderer(this);
		left_hand.setRotationPoint(3.35F, 0.05F, -0.35F);
		left_wing_rear.addChild(left_hand);

		left_index = new ModelRenderer(this);
		left_index.setRotationPoint(-0.15F, -0.05F, -0.075F);
		left_hand.addChild(left_index);
		left_index.cubeList.add(new ModelBox(left_index, 11, 6, -0.05F, -0.05F, -0.225F, 0, 0, 0, 0.0F, false));

		left_middle = new ModelRenderer(this);
		left_middle.setRotationPoint(0.0F, -0.05F, -0.075F);
		left_hand.addChild(left_middle);
		left_middle.cubeList.add(new ModelBox(left_middle, 11, 6, -0.05F, -0.05F, -0.225F, 0, 0, 0, 0.0F, false));

		left_ring = new ModelRenderer(this);
		left_ring.setRotationPoint(0.15F, -0.05F, -0.075F);
		left_hand.addChild(left_ring);
		left_ring.cubeList.add(new ModelBox(left_ring, 11, 6, -0.05F, -0.05F, -0.225F, 0, 0, 0, 0.0F, false));

		left_wing_elbow = new ModelRenderer(this);
		left_wing_elbow.setRotationPoint(3.55F, -0.05F, -0.225F);
		left_wing_rear.addChild(left_wing_elbow);

		left_wing_elbow_r1 = new ModelRenderer(this);
		left_wing_elbow_r1.setRotationPoint(-0.0934F, 0.05F, 0.2712F);
		left_wing_elbow.addChild(left_wing_elbow_r1);
		setRotationAngle(left_wing_elbow_r1, 0.0F, -0.1745F, 0.0F);
		left_wing_elbow_r1.cubeList
				.add(new ModelBox(left_wing_elbow_r1, 0, 4, -0.05F, -0.05F, -0.15F, 3, 0, 2, 0.0F, false));

		left_wing_elbow_r2 = new ModelRenderer(this);
		left_wing_elbow_r2.setRotationPoint(-0.05F, 0.15F, 0.025F);
		left_wing_elbow.addChild(left_wing_elbow_r2);
		setRotationAngle(left_wing_elbow_r2, 0.0F, -0.1745F, 0.0F);
		left_wing_elbow_r2.cubeList
				.add(new ModelBox(left_wing_elbow_r2, 7, 13, -0.05F, -0.25F, -0.15F, 3, 0, 0, 0.0F, true));

		midSection = new ModelRenderer(this);
		midSection.setRotationPoint(0.0F, 0.0F, 0.0F);
		body.addChild(midSection);
		midSection.cubeList.add(new ModelBox(midSection, 9, 12, -0.75F, -0.5F, 0.0F, 1, 0, 1, 0.0F, false));
		midSection.cubeList.add(new ModelBox(midSection, 1, 12, -0.55F, 0.4F, 0.0F, 1, 0, 1, 0.0F, false));
		midSection.cubeList.add(new ModelBox(midSection, 1, 12, 0.55F, 0.4F, 0.0F, 0, 0, 1, 0.0F, false));
		midSection.cubeList.add(new ModelBox(midSection, 1, 12, -0.65F, 0.4F, 0.0F, 0, 0, 1, 0.0F, false));

		pelvis = new ModelRenderer(this);
		pelvis.setRotationPoint(0.0F, -0.0208F, 1.625F);
		midSection.addChild(pelvis);
		pelvis.cubeList.add(new ModelBox(pelvis, 10, 12, -0.55F, -0.3792F, -0.125F, 1, 0, 1, 0.0F, true));
		pelvis.cubeList.add(new ModelBox(pelvis, 3, 12, -0.45F, 0.3708F, -0.125F, 0, 0, 0, 0.0F, false));
		pelvis.cubeList.add(new ModelBox(pelvis, 3, 12, -0.35F, 0.3708F, 0.675F, 0, 0, 0, 0.0F, false));

		left_leg_rear = new ModelRenderer(this);
		left_leg_rear.setRotationPoint(0.65F, -0.0042F, 0.775F);
		pelvis.addChild(left_leg_rear);
		left_leg_rear.cubeList.add(new ModelBox(left_leg_rear, 7, 12, -0.1F, -0.175F, -0.2F, 0, 0, 1, 0.0F, false));

		left_leg_front = new ModelRenderer(this);
		left_leg_front.setRotationPoint(0.0F, 0.0F, 1.0F);
		left_leg_rear.addChild(left_leg_front);
		left_leg_front.cubeList.add(new ModelBox(left_leg_front, 7, 12, -0.1F, -0.175F, 0.0F, 0, 0, 0, 0.0F, false));

		left_foot = new ModelRenderer(this);
		left_foot.setRotationPoint(0.0F, 0.0F, 0.7F);
		left_leg_front.addChild(left_foot);
		left_foot.cubeList.add(new ModelBox(left_foot, 7, 12, -0.2F, -0.175F, 0.0F, 0, 0, 0, 0.0F, false));
		left_foot.cubeList.add(new ModelBox(left_foot, 7, 12, -0.2F, 0.375F, 0.1F, 0, 0, 0, 0.0F, false));

		left_claws = new ModelRenderer(this);
		left_claws.setRotationPoint(0.0F, 0.55F, 0.2F);
		left_foot.addChild(left_claws);
		left_claws.cubeList.add(new ModelBox(left_claws, 11, 5, -0.2F, -0.025F, -0.1F, 0, 0, 0, 0.0F, false));
		left_claws.cubeList.add(new ModelBox(left_claws, 11, 5, 0.1F, -0.025F, -0.1F, 0, 0, 0, 0.0F, false));
		left_claws.cubeList.add(new ModelBox(left_claws, 11, 5, -0.05F, -0.025F, -0.1F, 0, 0, 0, 0.0F, false));

		right_leg_rear = new ModelRenderer(this);
		right_leg_rear.setRotationPoint(-0.65F, -0.0042F, 0.775F);
		pelvis.addChild(right_leg_rear);
		right_leg_rear.cubeList.add(new ModelBox(right_leg_rear, 9, 13, -0.1F, -0.175F, -0.2F, 0, 0, 1, 0.0F, false));

		right_leg_front = new ModelRenderer(this);
		right_leg_front.setRotationPoint(0.0F, 0.0F, 1.0F);
		right_leg_rear.addChild(right_leg_front);
		right_leg_front.cubeList.add(new ModelBox(right_leg_front, 9, 13, -0.1F, -0.175F, 0.0F, 0, 0, 0, 0.0F, false));

		right_foot = new ModelRenderer(this);
		right_foot.setRotationPoint(0.0F, 0.0F, 0.7F);
		right_leg_front.addChild(right_foot);
		right_foot.cubeList.add(new ModelBox(right_foot, 9, 13, -0.2F, -0.175F, 0.0F, 0, 0, 0, 0.0F, false));
		right_foot.cubeList.add(new ModelBox(right_foot, 9, 13, -0.2F, 0.375F, 0.1F, 0, 0, 0, 0.0F, false));

		right_claws = new ModelRenderer(this);
		right_claws.setRotationPoint(0.0F, 0.55F, 0.2F);
		right_foot.addChild(right_claws);
		right_claws.cubeList.add(new ModelBox(right_claws, 11, 6, 0.1F, -0.025F, -0.1F, 0, 0, 0, 0.0F, false));
		right_claws.cubeList.add(new ModelBox(right_claws, 11, 6, -0.2F, -0.025F, -0.1F, 0, 0, 0, 0.0F, false));
		right_claws.cubeList.add(new ModelBox(right_claws, 11, 6, -0.05F, -0.025F, -0.1F, 0, 0, 0, 0.0F, false));

		tail = new ModelRenderer(this);
		tail.setRotationPoint(0.0F, -0.0542F, 1.075F);
		pelvis.addChild(tail);
		tail.cubeList.add(new ModelBox(tail, 11, 13, -0.2F, -0.225F, 0.0F, 0, 0, 0, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		root.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
		this.right_wing_rear.rotateAngleZ = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
		this.neck_rear.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.neck_rear.rotateAngleX = f4 / (180F / (float) Math.PI);
		this.left_wing_rear.rotateAngleZ = MathHelper.cos(f * 0.6662F) * f1;
		this.left_wing_elbow.rotateAngleZ = MathHelper.cos(f * 0.6662F) * f1;
		this.left_foot.rotateAngleX = MathHelper.cos(f * 1.0F) * -1.0F * f1;
		this.right_wing_elbow.rotateAngleZ = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
		this.right_foot.rotateAngleX = MathHelper.cos(f * 1.0F) * 1.0F * f1;
	}
}